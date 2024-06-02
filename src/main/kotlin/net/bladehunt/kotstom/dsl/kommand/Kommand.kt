package net.bladehunt.kotstom.dsl.kommand

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.bladehunt.kotstom.command.Kommand
import net.bladehunt.kotstom.command.KommandExecutorContext
import net.bladehunt.kotstom.coroutines.MinestomDispatcher
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.CommandSyntax

@DslMarker @Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE) annotation class KommandDSL

/**
 * `Kommand` builder DSL
 *
 * @see net.bladehunt.kotstom.command.Kommand
 * @author oglassdev
 */
data class KommandBuilder(
    var name: String = "",
    var defaultExecutor: CommandExecutor = CommandExecutor { _, _ -> },
    val syntaxes: ArrayList<CommandSyntax> = arrayListOf(),
    val subcommands: ArrayList<Command> = arrayListOf()
) {
    /**
     * Sets the default executor of the Kommand
     *
     * @author oglassdev
     */
    @KommandDSL
    inline fun defaultExecutor(crossinline block: @KommandDSL KommandExecutorContext.() -> Unit) =
        CommandExecutor { sender, context -> KommandExecutorContext(sender, context).block() }
            .also { defaultExecutor = it }

    /**
     * Sets the async default executor of the Kommand
     *
     * @param context The `CoroutineContext` to launch the coroutine in
     * @param block The suspending code to run
     * @author oglassdev
     */
    @KommandDSL
    inline fun defaultExecutorAsync(
        context: CoroutineContext = MinestomDispatcher,
        crossinline block: @KommandDSL suspend KommandExecutorContext.() -> Unit
    ) =
        CommandExecutor { sender, ctx ->
                CoroutineScope(context).launch { KommandExecutorContext(sender, ctx).block() }
            }
            .also { defaultExecutor = it }

    /**
     * Sets the blocking default executor of the Kommand
     *
     * @param block The suspending code to run
     * @author oglassdev
     */
    @KommandDSL
    inline fun defaultExecutorBlocking(
        crossinline block: @KommandDSL suspend KommandExecutorContext.() -> Unit
    ) =
        CommandExecutor { sender, ctx ->
                runBlocking { KommandExecutorContext(sender, ctx).block() }
            }
            .also { defaultExecutor = it }

    /**
     * Creates a subcommand using another `Kommand` builder
     *
     * @param block The suspending code to run
     * @author oglassdev
     */
    @KommandDSL
    inline fun subkommand(block: @KommandDSL KommandBuilder.() -> Unit): Kommand =
        KommandBuilder().apply(block).build().apply(subcommands::add)

    /**
     * Adds a subcommand to the command's subcommand
     *
     * @param command The subcommand to add
     * @author oglassdev
     */
    @KommandDSL fun subcommand(command: Command) = subcommands.add(command)

    /**
     * Builds the Kommand
     *
     * @return A `Kommand`
     * @author oglassdev
     */
    fun build(): Kommand {
        if (name.isBlank()) throw IllegalArgumentException("Name must not be blank!")

        return Kommand(name).also { kommand ->
            kommand.defaultExecutor = defaultExecutor
            syntaxes.forEach { syntax -> kommand.syntaxes.add(syntax) }
            subcommands.forEach { subcommand -> kommand.addSubcommand(subcommand) }
        }
    }
}

/**
 * `Kommand` builder DSL
 *
 * @see net.bladehunt.kotstom.command.Kommand
 * @author oglassdev
 */
@KommandDSL
inline fun kommand(block: @KommandDSL KommandBuilder.() -> Unit): Kommand =
    KommandBuilder().apply(block).build()
