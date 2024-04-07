package net.bladehunt.kstom.dsl

import net.bladehunt.kstom.util.MinestomRunnable
import net.minestom.server.timer.ExecutionType
import net.minestom.server.timer.TaskSchedule

@DslMarker
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class RunnableDsl

data class RunnableBuilder(
    var delaySchedule: TaskSchedule = TaskSchedule.immediate(),
    var repeatSchedule: TaskSchedule = TaskSchedule.stop(),
    var executionType: ExecutionType = ExecutionType.SYNC,
    private var block: MinestomRunnable.() -> Unit = {}
) {
    @RunnableDsl
    fun run(block: @RunnableDsl MinestomRunnable.() -> Unit) {
        this.block = block
    }

    fun build(): MinestomRunnable = object : MinestomRunnable(delaySchedule, repeatSchedule, executionType) {
        override fun run() = block()
    }
}

@RunnableDsl
inline fun runnable(block: @RunnableDsl RunnableBuilder.() -> Unit): MinestomRunnable = RunnableBuilder().apply(block).build()