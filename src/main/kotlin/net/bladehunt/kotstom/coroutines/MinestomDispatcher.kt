package net.bladehunt.kotstom.coroutines

import kotlinx.coroutines.asCoroutineDispatcher
import net.bladehunt.kotstom.SchedulerManager

val MinestomDispatcher = SchedulerManager.asCoroutineDispatcher()