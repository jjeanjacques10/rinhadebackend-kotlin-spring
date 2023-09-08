package com.jjeanjacques.rinhadebackend.application.service

import com.jjeanjacques.rinhadebackend.application.repository.PersonRepository
import com.jjeanjacques.rinhadebackend.application.service.exception.PersonNotFoundException
import com.jjeanjacques.rinhadebackend.domain.Person
import org.springframework.stereotype.Service

@Service
class PersonService(
        val personRepository: PersonRepository
) {
    fun createPerson(person: Person) {
        personRepository.save(person)
    }

    fun findPersonById(id: Long): Person? {
        return personRepository.findById(id)
                .orElseThrow { PersonNotFoundException("Pessoa com id $id não encontrada") }
    }

    fun search(term: String?): List<Person> {
        var people = personRepository.findAll()
        if (people.isEmpty()) throw PersonNotFoundException("Pessoas nacao encontradas na base")
        return people
    }

    fun getCountPeople(): Long {
        return personRepository.count().or(0)
    }
}