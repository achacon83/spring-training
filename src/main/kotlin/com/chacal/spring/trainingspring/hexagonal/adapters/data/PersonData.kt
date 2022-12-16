package com.chacal.spring.trainingspring.hexagonal.adapters.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class PersonData(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    var name: String,
    var age: Int
    ) {

    constructor() : this(null, "", 0) {

    }

}