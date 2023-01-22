package com.chacal.spring.trainingspring.verticalslice.features.persons

import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonData
import com.chacal.spring.trainingspring.hexagonal.adapters.data.PersonDataRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.argThat
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

//@SpringBootTest
@ExtendWith(MockitoExtension::class)
internal class AddPersonTest {
    @Mock
    lateinit var pesonDataRepository: PersonDataRepository

    @BeforeEach
    fun setup() {
        Mockito.`when`(pesonDataRepository.save(any<PersonData>())).thenAnswer { i ->
            (i.arguments[0] as PersonData).also {
                it.id = 1
            }
        }
    }

    @Test
    fun `when add person then returns response`() {
        val sut = AddPerson.Controller(pesonDataRepository)

        val response = sut.addPerson(AddPerson.Controller.Body("Pedro",20))
        assertNotNull(response)
    }

    @Test
    fun `when add person then returns status 200`() {
        val sut = AddPerson.Controller(pesonDataRepository)

        val response = sut.addPerson(AddPerson.Controller.Body("Pedro",20))
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `when add person then returns id`() {
        val sut = AddPerson.Controller(pesonDataRepository)

        val response = sut.addPerson(AddPerson.Controller.Body("Pedro",20))
        assertEquals(1, response.body!!)
    }

    @Test
    fun `when add person then does save into repository`() {
        val sut = AddPerson.Controller(pesonDataRepository)

        sut.addPerson(AddPerson.Controller.Body("Pedro",20))
        Mockito.verify(pesonDataRepository).save(any())
    }
}