package com.jjeanjacques.rinhadebackend.application.service

import com.jjeanjacques.rinhadebackend.application.repository.PersonRepository
import com.jjeanjacques.rinhadebackend.application.service.exception.PersonNotFoundException
import com.jjeanjacques.rinhadebackend.domain.Person
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.UUID

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
        if (term.isNullOrBlank()) return personRepository.findAll()
        val people = personRepository.searchByTerm(term)
        //if (people.isEmpty()) throw PersonNotFoundException("Pessoas nacao encontradas na base")
        return people
    }

    fun getCountPeople(): Long {
        var count: Long = 0
        try {
            count = personRepository.count()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return count
    }
}