package com.chacal.spring.trainingspring.verticalslice

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import org.springframework.stereotype.Service

@Service
class PersonsApp(personDataRepository: PersonDataRepository) {

    /*val handlerMediator = HandlerMediator(listOf(
        GetPersonsFeature.GetPersonsHandler(personDataRepository)
    ))

    override fun <T : Any> invoke(request: Request<T>): T {
        return handlerMediator(request)

    }*/

}