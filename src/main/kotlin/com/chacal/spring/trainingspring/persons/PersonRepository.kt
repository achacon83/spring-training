package com.chacal.spring.trainingspring.persons

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: CrudRepository<PersonEntity, Int> {
}