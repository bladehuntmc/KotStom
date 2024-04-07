package net.bladehunt.kstom.dsl.kommand

import net.bladehunt.kstom.command.Kommand
import net.bladehunt.kstom.command.KommandExecutorContext
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.CommandSyntax

@DslMarker
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class KommandDSL

data class KommandBuilder(
    var name: String = "",
    var defaultExecutor: CommandExecutor = CommandExecutor { _, _ -> },
    val syntaxes: ArrayList<CommandSyntax> = arrayListOf()
) {
    @KommandDSL
    inline fun default(crossinline block: @KommandDSL KommandExecutorContext.() -> Unit) = CommandExecutor { sender, context ->
        KommandExecutorContext(sender, context).block()
    }.also { defaultExecutor = it }

    fun build(): Kommand {
        if (name.isBlank()) throw IllegalArgumentException("Name must not be blank!")

        return Kommand(name).also { kommand ->
            kommand.defaultExecutor = defaultExecutor
            syntaxes.forEach { syntax ->
                kommand.syntaxes.add(syntax)
            }
        }
    }
}

@KommandDSL
fun kommand(block: @KommandDSL KommandBuilder.() -> Unit): Kommand = KommandBuilder().apply(block).build()