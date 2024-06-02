package net.bladehunt.kotstom.coroutines

import kotlinx.coroutines.asCoroutineDispatcher
import net.bladehunt.kotstom.SchedulerManager

/**
 * A `CoroutineDispatcher` that uses Minestom's SchedulerManager to schedule tasks. Note: These are
 * not properly 'asynchronous.' If you need async, use the default dispatcher.
 *
 * @author oglassdev
 */
val MinestomDispatcher = SchedulerManager.asCoroutineDispatcher()
