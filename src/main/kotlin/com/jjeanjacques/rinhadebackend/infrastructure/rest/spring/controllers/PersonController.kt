package com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.jjeanjacques.rinhadebackend.application.service.PersonService
import com.jjeanjacques.rinhadebackend.application.service.ValidationService
import com.jjeanjacques.rinhadebackend.domain.Person
import com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.dto.CreatePersonResponseDto
import com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.dto.PersonDto
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.UUID
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/pessoas")
class PersonController(
        val personService: PersonService,
        val validationService: ValidationService,
        val objectMapper: ObjectMapper
) {

    @PostMapping
    fun createPerson(@Valid @RequestBody personDto: PersonDto): ResponseEntity<CreatePersonResponseDto> {
        validationService.validPerson(personDto)
        val person = objectMapper.convertValue(personDto, Person::class.java)
        personService.createPerson(person)
        return ResponseEntity.created(URI.create("/pessoas/${person.id}")).body(CreatePersonResponseDto("Person Created"))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Person> {
        val person = personService.findPersonById(UUID.fromString(id))
        return ResponseEntity.ok(person)
    }

    @GetMapping
    fun searchPeople(@RequestParam(name = "t", required = false) term: String?): ResponseEntity<List<Person>> {
        val people = personService.search(term)
        return ResponseEntity.ok(people)
    }

}