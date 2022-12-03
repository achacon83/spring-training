package com.chacal.spring.trainingspring.persons

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/persons")
class PersonController {

    @GetMapping("/{id}")
    fun getPerson(@PathVariable id: Int): PersonResponse {
        return PersonResponse(id, "Pedro", 15)
    }

    @PostMapping()
    fun addPerson(@RequestBody body: CreatePersonRequest): CreatedPersonResponse {
        return CreatedPersonResponse(1)
    }

}