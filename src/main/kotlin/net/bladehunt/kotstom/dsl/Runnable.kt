package net.bladehunt.kotstom.dsl

import net.bladehunt.kotstom.util.MinestomRunnable
import net.minestom.server.timer.ExecutionType
import net.minestom.server.timer.TaskSchedule

@DslMarker @Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE) annotation class RunnableDSL

data class RunnableBuilder(
    var delay: TaskSchedule = TaskSchedule.immediate(),
    var repeat: TaskSchedule = TaskSchedule.stop(),
    var executionType: ExecutionType = ExecutionType.SYNC,
    private var block: MinestomRunnable.() -> Unit = {}
) {
    @RunnableDSL
    fun run(block: @RunnableDSL MinestomRunnable.() -> Unit) {
        this.block = block
    }

    fun build(): MinestomRunnable =
        object : MinestomRunnable(delay, repeat, executionType) {
            override fun run() = block()
        }
}

@RunnableDSL
inline fun runnable(block: @RunnableDSL RunnableBuilder.() -> Unit): MinestomRunnable =
    RunnableBuilder().apply(block).build()
