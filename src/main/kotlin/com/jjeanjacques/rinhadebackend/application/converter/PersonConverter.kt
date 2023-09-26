package com.jjeanjacques.rinhadebackend.application.converter

import com.jjeanjacques.rinhadebackend.domain.Person
import com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.dto.PersonDto

class PersonConverter {
    companion object {
        fun convertPersonDtoToEntity(personDto: PersonDto): Person = Person(
            nickName = personDto.nickName,
            name = personDto.name,
            birthDay = personDto.birthDay,
            stack = if (personDto.stack != null) {
                personDto.stack.joinToString(";")
            } else null
        )

        fun convertPersonToDto(person: Person): PersonDto =
            PersonDto(
                id = person.id,
                nickName = person.nickName,
                name = person.name,
                birthDay = person.birthDay,
                stack = if (person.stack != null) {
                    person.stack!!.split(";")
                } else null
            )
    }
}