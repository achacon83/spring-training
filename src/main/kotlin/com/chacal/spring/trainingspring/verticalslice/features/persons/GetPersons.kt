package com.chacal.spring.trainingspring.verticalslice.features.persons

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonData
import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import com.trendyol.kediatr.Mediator
import com.trendyol.kediatr.Query
import com.trendyol.kediatr.QueryHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity

class GetPersons {
    @RestController
    @RequestMapping("/\${persons.controller.path}")
    class GetPersonsController(val mediator: Mediator) {
        @GetMapping()
        suspend fun getPersons(): ResponseEntity<GetPersonsViewModel> {
            val viewModel = mediator.send(GetPersonsQuery())
            return ResponseEntity.ok(viewModel)
        }
    }

    data class PersonViewModel(val id: Int, val name: String, val age: Int)
    data class GetPersonsViewModel(val persons: List<PersonViewModel>)

    class GetPersonsQuery: Query<GetPersonsViewModel>

    class GetPersonsHandler(val personDataRepository: PersonDataRepository): QueryHandler<GetPersonsQuery, GetPersonsViewModel> {

        override suspend fun handle(query: GetPersonsQuery): GetPersonsViewModel {
            return personDataRepository
                .findAll()
                .map { it.toPersonViewModel() }
                .toGetPersonsViewModel()
        }

        fun PersonData.toPersonViewModel(): PersonViewModel {
            return PersonViewModel(this.id!!, this.name, this.age)
        }

        fun List<PersonViewModel>.toGetPersonsViewModel(): GetPersonsViewModel {
            return GetPersonsViewModel(this)
        }
    }
}