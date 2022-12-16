package com.chacal.spring.trainingspring.hexagonal.adapters

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonData
import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import com.chacal.spring.trainingspring.hexagonal.domain.Person
import com.chacal.spring.trainingspring.hexagonal.domain.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonsServiceAdapter(val personDataRepository: PersonDataRepository): PersonRepository {

    override fun save(person: Person): Person {
        return personDataRepository
            .save(person.toData())
            .toDomain()
    }

    override fun findById(id: Int): Person? {
        return personDataRepository
            .findById(id)
            .map { it.toDomain() }
            .orElse(null)
    }

    fun PersonData.toDomain(): Person {
        return Person(
            this.id ?: throw RuntimeException("Data doesn't have an id"),
            this.name,
            this.age
        )
    }

    fun Person.toData(): PersonData {
        return PersonData(
            this.id,
            this.name,
            this.age
        )
    }
}