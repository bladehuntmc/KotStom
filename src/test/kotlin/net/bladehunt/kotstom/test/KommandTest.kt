package net.bladehunt.kotstom.test

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import net.bladehunt.kotstom.CommandManager
import net.bladehunt.kotstom.dsl.kommand.kommand
import net.bladehunt.kotstom.dsl.kommand.syntax
import net.minestom.server.command.builder.arguments.ArgumentString

class KommandTest :
    FunSpec({
        beforeAny { SERVER }
        test("should register commands") {
            CommandManager.register(kommand { name = "test-command" })
            CommandManager.commandExists("test-command") shouldBe true
        }
        test("should create subcommands") {
            val command = kommand {
                name = "subcommand-test"
                subkommand { name = "first" }
                subkommand { name = "second" }
            }
            command.subcommands.size shouldBe 2
        }
        test("should create arguments") {
            val command = kommand {
                val value = ArgumentString("value")
                name = "syntax-test"
                syntax(value) { value() }
            }
            command.syntaxes.first().arguments.first().id shouldBe "value"
        }
    })
