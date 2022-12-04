package com.chacal.spring.trainingspring.persons.adapters.data

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonDataRepository: CrudRepository<PersonData, Int> {
}