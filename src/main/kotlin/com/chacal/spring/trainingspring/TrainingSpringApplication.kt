package com.chacal.spring.trainingspring

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import com.chacal.spring.trainingspring.verticalslice.ManualDependencyProvider
import com.chacal.spring.trainingspring.verticalslice.features.AddPerson
import com.chacal.spring.trainingspring.verticalslice.features.GetPersonsFeature
import com.chacal.spring.trainingspring.verticalslice.features.IncreasePersonAge
import com.trendyol.kediatr.DependencyProvider
import com.trendyol.kediatr.Mediator
import com.trendyol.kediatr.MediatorBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
class TrainingSpringApplication {

	@Autowired
	lateinit var personDataRepository: PersonDataRepository

	fun handlers(): HashMap<Class<*>, Any> = hashMapOf(
		GetPersonsFeature.GetPersonsHandler::class.java to GetPersonsFeature.GetPersonsHandler(personDataRepository),
		IncreasePersonAge.CommandHandler::class.java to IncreasePersonAge.CommandHandler(personDataRepository)
	)

	fun provider(): DependencyProvider = ManualDependencyProvider(handlers())

	@Bean
	fun mediator(): Mediator = MediatorBuilder(provider()).build()
}

fun main(args: Array<String>) {
	runApplication<TrainingSpringApplication>(*args)
}
