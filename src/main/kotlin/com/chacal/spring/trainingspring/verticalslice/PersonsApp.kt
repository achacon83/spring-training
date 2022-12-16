package com.chacal.spring.trainingspring.verticalslice

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import com.chacal.spring.trainingspring.verticalslice.core.Mediator
import com.chacal.spring.trainingspring.verticalslice.core.Request
import com.chacal.spring.trainingspring.verticalslice.core.RequestHandler
import com.chacal.spring.trainingspring.verticalslice.features.GetPersonsFeature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.reflect.ParameterizedType
import kotlin.reflect.full.isSubclassOf

typealias MapHandler = Map<Request<Any>, RequestHandler<Request<Any>, Any>>

@Component
class PersonsApp(): Mediator {
    @Autowired
    lateinit var personDataRepository: PersonDataRepository

    val handlerMediator = HandlerMediator(listOf(
        GetPersonsFeature.GetPersonsHandler(personDataRepository)
    ))

    override fun <T : Any> invoke(request: Request<T>): T {
        return handlerMediator(request)
    }

}