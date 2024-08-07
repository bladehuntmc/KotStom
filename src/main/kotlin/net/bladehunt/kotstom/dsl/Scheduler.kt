package net.bladehunt.kotstom.dsl

import java.util.function.Supplier
import net.minestom.server.timer.*

inline fun <T : Schedulable> T.scheduleTask(
    executionType: ExecutionType = ExecutionType.TICK_START,
    delay: TaskSchedule = TaskSchedule.immediate(),
    repeat: TaskSchedule,
    crossinline block: (T) -> Unit
): Task = scheduler().scheduleTask(executionType, delay, repeat) { block(this) }

inline fun Scheduler.scheduleTask(
    executionType: ExecutionType = ExecutionType.TICK_START,
    delay: TaskSchedule = TaskSchedule.immediate(),
    repeat: TaskSchedule,
    crossinline block: () -> Unit
): Task {
    return this.submitTask(
        object : Supplier<TaskSchedule> {
            private var hasExecuted = false

            override fun get(): TaskSchedule {
                if (!hasExecuted) {
                    hasExecuted = true
                    return delay
                }

                block()

                return repeat
            }
        },
        executionType)
}

inline fun <T : Schedulable> T.scheduleTask(
    executionType: ExecutionType = ExecutionType.TICK_START,
    delay: TaskSchedule = TaskSchedule.immediate(),
    crossinline block: (T) -> TaskSchedule
): Task = scheduler().scheduleTask(executionType, delay) { block(this) }

inline fun Scheduler.scheduleTask(
    executionType: ExecutionType = ExecutionType.TICK_START,
    delay: TaskSchedule = TaskSchedule.immediate(),
    crossinline block: () -> TaskSchedule
): Task {
    return this.submitTask(
        object : Supplier<TaskSchedule> {
            private var hasExecuted = false

            override fun get(): TaskSchedule {
                if (!hasExecuted) {
                    hasExecuted = true
                    return delay
                }

                return block()
            }
        },
        executionType)
}
