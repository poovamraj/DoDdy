package com.github.dod.doddy.core

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import kotlin.reflect.KClass
import kotlin.reflect.full.createType
import kotlin.reflect.full.functions

class Commands {

    private val commandFunctions = mutableMapOf<String, CommandFunction>()

    fun register(caller: Module) {
        val module = caller.javaClass.kotlin
        module.functions.forEach { function ->
            val parameters = function.parameters
            if (parameters.size > 1) {
                val commandAnnotation = function.annotations.find { annotation -> annotation is Command }
                if (commandAnnotation != null && commandAnnotation is Command) {
                    if (parameters[1].type == MessageReceivedEvent::class.createType()) {
                        commandAnnotation.names.forEach { commandName ->
                            commandFunctions[commandName] = CommandFunction(
                                    caller,
                                    function,
                                    parameters.drop(2),
                                    parameters.last().type == List::class
                            )
                        }
                    }
                }
            }
        }
    }

    fun call(name: String, event: MessageReceivedEvent, args: List<String>): CommandResult {
        val commandFunction = commandFunctions[name] ?: return CommandNotFound(name)
        return commandFunction.call(event, args)
    }
}