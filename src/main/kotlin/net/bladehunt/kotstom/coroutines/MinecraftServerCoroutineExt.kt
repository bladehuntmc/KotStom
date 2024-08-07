package net.bladehunt.kotstom.coroutines

import java.net.SocketAddress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.minestom.server.MinecraftServer

/**
 * Starts a MinecraftServer using Dispatchers.IO This creates the MinecraftServer on another thread.
 *
 * @param address The address to start the server on
 * @param port The port
 * @author oglassdev
 */
suspend fun MinecraftServer.startSuspending(address: String, port: Int): Unit =
    withContext(Dispatchers.IO) { start(address, port) }

/**
 * Starts a MinecraftServer using Dispatchers.IO This creates the MinecraftServer on another thread.
 *
 * @param address The address to start the server on
 * @author oglassdev
 */
suspend fun MinecraftServer.startSuspending(address: SocketAddress): Unit =
    withContext(Dispatchers.IO) { start(address) }
