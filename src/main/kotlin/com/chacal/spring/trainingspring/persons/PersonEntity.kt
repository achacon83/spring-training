package com.chacal.spring.trainingspring.persons

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
open class PersonEntity: Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    override var name: String = ""
    override var age: Int = 0
}