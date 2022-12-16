package com.chacal.spring.trainingspring.verticalslice.features

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonData
import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import com.chacal.spring.trainingspring.verticalslice.core.Mediator
import com.chacal.spring.trainingspring.verticalslice.core.Request
import com.chacal.spring.trainingspring.verticalslice.core.RequestHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity

class GetPersonsFeature {
    @RestController
    @RequestMapping("verticalslide/persons")
    class GetPersonsController(val mediator: Mediator) {
        @GetMapping()
        fun getPersons(): ResponseEntity<GetPersonsViewModel> {
            val viewModel = mediator(GetPersonsQuery())
            return ResponseEntity.ok(viewModel)
        }
    }

    data class PersonViewModel(val id: Int, val name: String, val age: Int)
    data class GetPersonsViewModel(val persons: List<PersonViewModel>)

    class GetPersonsQuery: Request<GetPersonsViewModel> {
        // Argumentos del comando, en este caso no tengo ninguno
    }

    class GetPersonsHandler(val personDataRepository: PersonDataRepository): RequestHandler<GetPersonsQuery, GetPersonsViewModel> {
        override operator fun invoke(request: GetPersonsQuery): GetPersonsViewModel {
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