package com.chacal.spring.trainingspring.persons.rest

import com.chacal.spring.trainingspring.persons.data.PersonEntity
import com.chacal.spring.trainingspring.persons.data.PersonRepository
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
            .map { it.id?.let { it1 -> PersonResponse(it1, it.name, it.age) } }
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found") }
    }

    @PostMapping()
    fun addPerson(@RequestBody body: CreatePersonRequest): CreatedPersonResponse {
        return personRepository
            .save( body.toNewEntity() )
            .toCreatedResponse()
    }

    fun CreatePersonRequest.toNewEntity(): PersonEntity {
        return PersonEntity().also {
            it.name = this.name
            it.age = this.age
        }
    }

    fun PersonEntity.toCreatedResponse(): CreatedPersonResponse {
        this.id?.let { return CreatedPersonResponse(it) }
        throw RuntimeException("Person doesn't have an id")
    }
}