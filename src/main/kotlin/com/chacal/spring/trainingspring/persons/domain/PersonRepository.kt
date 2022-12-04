package com.chacal.spring.trainingspring.persons.domain

interface PersonRepository {
    fun save(person: Person): Person
    fun findById(id: Int): Person?
}