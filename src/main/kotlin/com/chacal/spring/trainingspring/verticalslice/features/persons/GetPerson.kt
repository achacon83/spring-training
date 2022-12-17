package com.chacal.spring.trainingspring.verticalslice.features.persons

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonData
import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import com.trendyol.kediatr.Mediator
import com.trendyol.kediatr.Query as IQuery
import com.trendyol.kediatr.QueryHandler as IQueryHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

class GetPerson {
    @RestController
    @RequestMapping("/\${person-controller.path}")
    class GetPersonsController(val mediator: Mediator) {
        @GetMapping("/{id}")
        suspend fun getPerson(@PathVariable id: Int): ResponseEntity<GetPersonViewModel> {
            val viewModel = mediator.send(Query(id))
            return ResponseEntity.ok(viewModel)
        }
    }

    data class GetPersonViewModel(val name: String, val age: Int)

    class Query(val id: Int): IQuery<GetPersonViewModel>

    class QueryHandler(val personDataRepository: PersonDataRepository): IQueryHandler<Query, GetPersonViewModel> {

        override suspend fun handle(query: Query): GetPersonViewModel {
            return personDataRepository
                .findById(query.id)
                .orElseThrow()
                .toGetPersonViewModel()
        }

        fun PersonData.toGetPersonViewModel(): GetPersonViewModel {
            return GetPersonViewModel(this.name, this.age)
        }
    }
}