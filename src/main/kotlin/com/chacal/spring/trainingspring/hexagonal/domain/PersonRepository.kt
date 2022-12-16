package com.chacal.spring.trainingspring.hexagonal.domain

interface PersonRepository {
    fun save(person: Person): Person
    fun findById(id: Int): Person?
}