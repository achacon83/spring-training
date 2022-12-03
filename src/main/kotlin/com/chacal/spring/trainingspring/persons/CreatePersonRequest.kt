package com.chacal.spring.trainingspring.persons

data class CreatePersonRequest(override val name: String, override val age: Int): Person
