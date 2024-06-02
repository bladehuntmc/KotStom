package net.bladehunt.kotstom.extension

import net.minestom.server.timer.ExecutionType
import net.minestom.server.timer.Schedulable
import net.minestom.server.timer.Scheduler
import net.minestom.server.timer.TaskSchedule
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Deprecated("", ReplaceWith("scheduler()"))
inline val Schedulable.scheduler
    get() = scheduler()

/** Suspends a coroutine until a delay is met */
suspend fun Scheduler.await(
    delay: TaskSchedule,
    executionType: ExecutionType = ExecutionType.TICK_START
) = suspendCoroutine {
    this.buildTask { it.resume(Unit) }.delay(delay).executionType(executionType).schedule()
}

/** Suspends a coroutine until a delay is met */
suspend fun Schedulable.await(
    delay: TaskSchedule,
    executionType: ExecutionType = ExecutionType.TICK_START
) = suspendCoroutine {
    scheduler().buildTask { it.resume(Unit) }.delay(delay).executionType(executionType).schedule()
}
