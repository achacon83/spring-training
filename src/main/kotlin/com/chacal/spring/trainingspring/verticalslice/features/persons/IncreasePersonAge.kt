package com.chacal.spring.trainingspring.verticalslice.features.persons

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import com.trendyol.kediatr.Mediator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class IncreasePersonAge {
    @RestController
    @RequestMapping("/\${persons.controller.path}")
    class Controller(val mediator: Mediator) {
        @PostMapping("/{id}/increaseAge")
        suspend fun addPerson(@PathVariable id: Int): ResponseEntity<Void> {
            val id = mediator.send(Command(id))
            return ResponseEntity.ok().build()
        }
    }

    class Command(val id: Int): com.trendyol.kediatr.Command

    class CommandHandler(private val personDataRepository: PersonDataRepository): com.trendyol.kediatr.CommandHandler<Command> {
        override suspend fun handle(command: Command) {
            personDataRepository
                .findById(command.id)
                .orElseThrow()
                .also { it.age += 1 }
                .also { personDataRepository.save(it) }
        }
    }
}