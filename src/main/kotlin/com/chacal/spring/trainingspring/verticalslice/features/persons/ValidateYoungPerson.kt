package com.chacal.spring.trainingspring.verticalslice.features.persons
import com.chacal.spring.trainingspring.verticalslice.core.Domain
import com.trendyol.kediatr.Command as ICommand
import com.trendyol.kediatr.CommandHandler as ICommandHandler

class ValidateYoungPerson {

    data class Command(val id: Int, val maxAge: Int): ICommand

    class CommandHandler(private val findPersonById: (id: Int) -> Person): ICommandHandler<Command> {
        override suspend fun handle(command: Command) {
            findPersonById(command.id)
                .also {
                    require(it.isYoung(command.maxAge)) {
                        "This person is older than ${command.maxAge}"
                    }
                }
        }
    }

    data class Person(val age: Int): Domain {
        fun isYoung(maxAge: Int): Boolean = age <= maxAge
    }
}