package com.chacal.spring.trainingspring.hexagonal.adapters.data

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonDataRepository: CrudRepository<PersonData, Int> {
}