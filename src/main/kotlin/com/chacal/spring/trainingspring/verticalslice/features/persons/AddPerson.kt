package com.chacal.spring.trainingspring.verticalslice.features.persons

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonData
import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class AddPerson {
    @RestController
    @RequestMapping("/\${persons.controller.path}")
    class Controller(private val personDataRepository: PersonDataRepository) {
        @PostMapping()
        fun addPerson(@RequestBody body: Body): ResponseEntity<Int> {
            val id = personDataRepository.save(body.toData()).id!!
            return ResponseEntity.ok(id)
        }

        data class Body(val name: String, val age: Int) {
            fun toData(): PersonData {
                return PersonData(null, name, age)
            }
        }
    }
}