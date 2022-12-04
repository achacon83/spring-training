package com.chacal.spring.trainingspring.persons.rest

import com.chacal.spring.trainingspring.persons.domain.Person
import com.chacal.spring.trainingspring.persons.domain.PersonRepository
import com.chacal.spring.trainingspring.persons.rest.model.PersonResponse
import com.chacal.spring.trainingspring.persons.rest.model.CreatePersonRequest
import com.chacal.spring.trainingspring.persons.rest.model.CreatedPersonResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/persons")
class PersonController {

    @Autowired
    lateinit var personRepository: PersonRepository

    @GetMapping("/{id}")
    fun getPerson(@PathVariable id: Int): PersonResponse {
        return personRepository
            .findById(id)
            ?.toPersonResponse() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found")
    }

    @PostMapping()
    fun addPerson(@RequestBody body: CreatePersonRequest): CreatedPersonResponse {
        return personRepository
            .save( body.toPerson() )
            .toCreatedPersonResponse()
    }

    fun CreatePersonRequest.toPerson(): Person {
        return Person.create(this.name, this.age)
    }

    fun Person.toPersonResponse(): PersonResponse {
        return PersonResponse(
            this.id ?: throw RuntimeException("Person doesn't have an id"),
            this.name,
            this.age
        )
    }

    fun Person.toCreatedPersonResponse(): CreatedPersonResponse {
        this.id?.let { return CreatedPersonResponse(it) }
        throw RuntimeException("Person doesn't have an id")
    }
}