package com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.controllers

import com.jjeanjacques.rinhadebackend.application.service.PersonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contagem-pessoas")
class CountController(
        val personService: PersonService
) {
    @GetMapping
    fun countPeople(): ResponseEntity<Long> {
        return ResponseEntity.ok(personService.getCountPeople())
    }
}