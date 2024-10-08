package net.bladehunt.kotstom.coroutines

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlinx.coroutines.*
import net.bladehunt.kotstom.SchedulerManager
import net.bladehunt.kotstom.dsl.scheduleTask
import net.minestom.server.timer.TaskSchedule

/**
 * A `CoroutineDispatcher` that uses Minestom's SchedulerManager to schedule tasks. Note: These are
 * not properly 'asynchronous.' If you need async, use Dispatchers.default.
 *
 * @author oglassdev
 */
@OptIn(InternalCoroutinesApi::class)
object MinestomDispatcher : CoroutineDispatcher(), Delay {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        SchedulerManager.execute(block)
    }

    override fun scheduleResumeAfterDelay(
        timeMillis: Long,
        continuation: CancellableContinuation<Unit>
    ) {
        SchedulerManager.scheduleTask(
            delay = TaskSchedule.millis(timeMillis), repeat = TaskSchedule.stop()) {
                continuation.resume(Unit)
            }
    }
}
