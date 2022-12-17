package com.chacal.spring.trainingspring.verticalslice.features.persons

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import com.chacal.spring.trainingspring.verticalslice.ManualDependencyProvider
import com.trendyol.kediatr.DependencyProvider
import com.trendyol.kediatr.Mediator
import com.trendyol.kediatr.MediatorBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PersonsConfig {
    @Autowired
    lateinit var personDataRepository: PersonDataRepository

    fun handlers(): HashMap<Class<*>, Any> = hashMapOf(
        GetPersons.GetPersonsHandler::class.java to GetPersons.GetPersonsHandler(personDataRepository),
        IncreasePersonAge.CommandHandler::class.java to IncreasePersonAge.CommandHandler(personDataRepository),
        GetPerson.QueryHandler::class.java to GetPerson.QueryHandler(personDataRepository)
    )

    fun provider(): DependencyProvider = ManualDependencyProvider(handlers())

    @Bean
    fun mediator(): Mediator = MediatorBuilder(provider()).build()
}