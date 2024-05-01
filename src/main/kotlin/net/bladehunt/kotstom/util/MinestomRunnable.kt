package net.bladehunt.kotstom.util

import net.bladehunt.kotstom.SchedulerManager
import net.minestom.server.timer.ExecutionType
import net.minestom.server.timer.Task
import net.minestom.server.timer.TaskSchedule

abstract class MinestomRunnable(
    private var delaySchedule: TaskSchedule = TaskSchedule.immediate(),
    private var repeatSchedule: TaskSchedule = TaskSchedule.stop(),
    private var executionType: ExecutionType = ExecutionType.SYNC
) : Runnable {
    private var task: Task? = null

    fun schedule(): Task = SchedulerManager.buildTask(this).let {
        if (delaySchedule != TaskSchedule.immediate()) it.delay(delaySchedule)
        if (repeatSchedule != TaskSchedule.stop()) it.repeat(repeatSchedule)
        if (executionType != ExecutionType.SYNC) it.executionType(executionType)

        it.schedule().also { task ->
            this.task = task
        }
    }
    fun cancel() = task?.cancel()
}