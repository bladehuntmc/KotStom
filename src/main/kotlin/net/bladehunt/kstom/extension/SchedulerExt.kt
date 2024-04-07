package net.bladehunt.kstom.extension

import net.minestom.server.timer.ExecutionType
import net.minestom.server.timer.Schedulable
import net.minestom.server.timer.TaskSchedule
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

inline val Schedulable.scheduler get() = scheduler()
suspend fun Schedulable.await(
    delay: TaskSchedule,
    executionType: ExecutionType = ExecutionType.SYNC
) = suspendCoroutine { scheduler.buildTask { it.resume(Unit) }.delay(delay).executionType(executionType).schedule() }