package net.bladehunt.kotstom.util

import net.bladehunt.kotstom.SchedulerManager
import net.minestom.server.timer.ExecutionType
import net.minestom.server.timer.Scheduler
import net.minestom.server.timer.Task
import net.minestom.server.timer.TaskSchedule

/**
 * A runnable that can be scheduled using a `Scheduler` with a delay/repeat schedule
 *
 * @author oglassdev
 */
abstract class MinestomRunnable(
    private var delaySchedule: TaskSchedule = TaskSchedule.immediate(),
    private var repeatSchedule: TaskSchedule = TaskSchedule.stop(),
    private var executionType: ExecutionType = ExecutionType.TICK_START
) : Runnable {
    private var task: Task? = null

    /**
     * Schedules the MinestomRunnable using the specified scheduler
     *
     * @param scheduler The scheduler to use to schedule the task
     */
    fun schedule(scheduler: Scheduler = SchedulerManager): Task =
        scheduler.buildTask(this).let {
            if (delaySchedule != TaskSchedule.immediate()) it.delay(delaySchedule)
            if (repeatSchedule != TaskSchedule.stop()) it.repeat(repeatSchedule)
            if (executionType != ExecutionType.TICK_START) it.executionType(executionType)

            it.schedule().also { task -> this.task = task }
        }

    fun cancel() = task?.cancel()
}
