package com.jjeanjacques.rinhadebackend.application.service

import com.jjeanjacques.rinhadebackend.application.repository.PersonRepository
import com.jjeanjacques.rinhadebackend.application.service.exception.PersonNotFoundException
import com.jjeanjacques.rinhadebackend.domain.Person
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
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

    fun search(term: String?): List<Person> {
        val limit: Pageable = PageRequest.of(0, 50)
        if (term.isNullOrBlank()) {
            return personRepository.findAll(limit).toList()
        }
        return personRepository.searchByTerm(
            term,
            pageable = limit
        )
    }

    fun getCountPeople(): Long {
        return try {
            personRepository.count()
        } catch (ex: Exception) {
            0
        }
    }
}