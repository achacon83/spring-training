package com.chacal.spring.trainingspring.persons

data class PersonResponse(val id: Int, override val name: String, override val age: Int) : Person
