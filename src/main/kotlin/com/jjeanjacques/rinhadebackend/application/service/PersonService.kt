package com.jjeanjacques.rinhadebackend.application.service

import com.jjeanjacques.rinhadebackend.application.converter.PersonConverter
import com.jjeanjacques.rinhadebackend.application.repository.PersonRepository
import com.jjeanjacques.rinhadebackend.application.service.exception.PersonNotFoundException
import com.jjeanjacques.rinhadebackend.domain.Person
import com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.dto.PersonDto
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*


@Service
@CacheConfig(cacheNames = ["person"])
class PersonService(
    val personRepository: PersonRepository
) {
    fun createPerson(person: Person) {
        personRepository.save(person)
    }

    @Cacheable(key = "#id")
    fun findPersonById(id: UUID): Person? {
        return personRepository.findById(id)
            .orElseThrow { PersonNotFoundException("Pessoa com id $id não encontrada") }
    }

    fun search(term: String): List<PersonDto> {
        return personRepository.searchByTerm(
            term,
            pageable = PageRequest.of(0, 50)
        ).map(PersonConverter::convertPersonToDto)
    }

    fun getCountPeople(): Long {
        return try {
            personRepository.count()
        } catch (ex: Exception) {
            0
        }
    }
}