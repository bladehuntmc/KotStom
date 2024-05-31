package net.bladehunt.kotstom.dsl.kommand

import net.bladehunt.kotstom.command.Kommand
import net.bladehunt.kotstom.command.KommandExecutorContext
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.CommandSyntax

@DslMarker @Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE) annotation class KommandDSL

data class KommandBuilder(
    var name: String = "",
    var defaultExecutor: CommandExecutor = CommandExecutor { _, _ -> },
    val syntaxes: ArrayList<CommandSyntax> = arrayListOf(),
    val subcommands: ArrayList<Command> = arrayListOf()
) {
    @KommandDSL
    inline fun default(crossinline block: @KommandDSL KommandExecutorContext.() -> Unit) =
        CommandExecutor { sender, context -> KommandExecutorContext(sender, context).block() }
            .also { defaultExecutor = it }

    @KommandDSL
    fun subkommand(block: @KommandDSL KommandBuilder.() -> Unit): Kommand =
        KommandBuilder().apply(block).build().also { subcommands.add(it) }

    @KommandDSL fun subcommand(command: Command) = subcommands.add(command)

    fun build(): Kommand {
        if (name.isBlank()) throw IllegalArgumentException("Name must not be blank!")

        return Kommand(name).also { kommand ->
            kommand.defaultExecutor = defaultExecutor
            syntaxes.forEach { syntax -> kommand.syntaxes.add(syntax) }
            subcommands.forEach { subcommand -> kommand.addSubcommand(subcommand) }
        }
    }
}

@KommandDSL
fun kommand(block: @KommandDSL KommandBuilder.() -> Unit): Kommand =
    KommandBuilder().apply(block).build()
