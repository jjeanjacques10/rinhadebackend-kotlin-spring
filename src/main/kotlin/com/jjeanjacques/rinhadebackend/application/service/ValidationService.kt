package com.jjeanjacques.rinhadebackend.application.service

import com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.dto.PersonDto
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.regex.Pattern

@Service
class ValidationService {
    fun validPerson(person: PersonDto) {
        when {
            person.name.isNullOrBlank() -> throw IllegalArgumentException("nome não deve ser nulo")
            person.nickName.isNullOrBlank() -> throw IllegalArgumentException("apelido não deve ser nulo")
            person.birthDay == null -> throw IllegalArgumentException("nascimento não deve ser nulo")
            !isValidDateFormat(person.birthDay) -> throw IllegalArgumentException("nascimento deve ter o formato AAAA-MM-DD")
        }
        person.stack?.forEach {
            if (it.length > 32) throw IllegalArgumentException("stack deve ser menor que 32 caracteres")
        }
    }

    private fun isValidDateFormat(birthDay: LocalDate?): Boolean {
        val datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$")
        return datePattern.matcher(birthDay.toString()).matches()
    }
}