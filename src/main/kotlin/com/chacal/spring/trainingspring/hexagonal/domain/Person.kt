package com.chacal.spring.trainingspring.hexagonal.domain

data class Person(val id: Int?, val name: String, val age: Int) {
    companion object {
        fun create(name: String, age: Int) = Person(null, name, age)
    }
}