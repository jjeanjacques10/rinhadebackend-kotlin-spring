package com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.jjeanjacques.rinhadebackend.application.service.PersonService
import com.jjeanjacques.rinhadebackend.application.service.ValidationService
import com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.dto.CreatePersonResponseDto
import com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.dto.PersonDto
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import io.mockk.spyk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDate


@ExtendWith(SpringExtension::class)
class PersonControllerTest {

    private val personService: PersonService = mockk()
    private val validationService: ValidationService = mockk()
    private val objectMapper = ObjectMapper()

    @SpyK
    var personController: PersonController = spyk(PersonController(personService, validationService, objectMapper))


    @BeforeEach
    fun setup() {
        val responseDto = ResponseEntity.ok(CreatePersonResponseDto("Person Created"))
        every { personController.createPerson(any()) } returns responseDto
    }

    @Test
    fun testPersonCreateEndpoint() {
        val responseExpected = CreatePersonResponseDto("Person Created")
        val request = PersonDto("JJ", "Jean", LocalDate.now(), listOf("Java", "Kotlin"))

        val response = personController.createPerson(request).body

        assertNotNull(response?.message)
        assertEquals(responseExpected.message, response?.message)
    }

}