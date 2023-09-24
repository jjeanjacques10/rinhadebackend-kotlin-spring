package com.jjeanjacques.rinhadebackend.application.service

import com.jjeanjacques.rinhadebackend.application.service.exception.IllegalArgumentTypeException
import com.jjeanjacques.rinhadebackend.application.service.exception.InvalidArgumentTypeException
import com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.dto.PersonDto
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.regex.Pattern

@Service
class ValidationService {
    fun validPerson(person: PersonDto) {
        when {
            person.nickName.isNullOrBlank() -> throw IllegalArgumentException("apelido não deve ser nulo")
            person.nickName.length > 32 -> throw IllegalArgumentException("apelido deve ser menor que 32 caracteres")
            isNumber(person.nickName) -> throw InvalidArgumentTypeException("nome deve ser uma string nao numerica")

            person.name.isNullOrBlank() -> throw IllegalArgumentException("nome não deve ser nulo")
            isNumber(person.name) -> throw IllegalArgumentTypeException("nome deve ser uma string nao numerica")
            person.name.length > 100 -> throw InvalidArgumentTypeException("apelido deve ser menor que 100 caracteres")

            person.birthDay == null -> throw IllegalArgumentException("nascimento não deve ser nulo")
            !isValidDateFormat(person.birthDay) -> throw IllegalArgumentException("nascimento deve ter o formato AAAA-MM-DD")
        }
        person.stack?.forEach {
            if (it.length > 32) throw IllegalArgumentException("stack deve ser menor que 32 caracteres")
            if (it.matches("-?\\d+(\\.\\d+)?".toRegex())) throw IllegalArgumentTypeException("stack deve ser uma string nao numerica")
        }
    }

    private fun isNumber(item: String) =
            item.matches("-?\\d+(\\.\\d+)?".toRegex())

    private fun isValidDateFormat(birthDay: LocalDate?): Boolean {
        val datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$")
        return datePattern.matcher(birthDay.toString()).matches()
    }
}