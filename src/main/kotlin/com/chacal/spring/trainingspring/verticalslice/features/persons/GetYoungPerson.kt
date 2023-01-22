package com.chacal.spring.trainingspring.verticalslice.features.persons

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonData
import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import com.chacal.spring.trainingspring.verticalslice.core.Domain
import com.trendyol.kediatr.Mediator
import com.trendyol.kediatr.Query as IQuery
import com.trendyol.kediatr.QueryHandler as IQueryHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

class GetYoungPerson {
    @RestController
    @RequestMapping("/\${persons.controller.path}/young")
    class GetPersonsController(val mediator: Mediator) {
        @GetMapping("/{id}")
        suspend fun getYoungPerson(@PathVariable id: Int): ResponseEntity<GetPersonViewModel> {
            val viewModel = mediator.send(Query(id, 40))
            return ResponseEntity.ok(viewModel)
        }
    }

    data class GetPersonViewModel(val name: String, val age: Int)

    class Query(val id: Int, val maxAge: Int): IQuery<GetPersonViewModel>

    class QueryHandler(val personDataRepository: PersonDataRepository): IQueryHandler<Query, GetPersonViewModel> {

        override suspend fun handle(query: Query): GetPersonViewModel {
            return personDataRepository
                .findById(query.id)
                .filter { it.toDomain().isYoung(query.maxAge) }
                .orElseThrow()
                .toGetPersonViewModel()
        }

        fun PersonData.toDomain(): PersonDomain {
            return PersonDomain(age)
        }

        fun PersonData.toGetPersonViewModel(): GetPersonViewModel {
            return GetPersonViewModel(this.name, this.age)
        }

    }

    data class PersonDomain(val age: Int): Domain {
        fun isYoung(maxAge: Int): Boolean = age <= maxAge
    }
}