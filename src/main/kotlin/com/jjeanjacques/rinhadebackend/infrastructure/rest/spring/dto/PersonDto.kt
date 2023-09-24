package com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class PersonDto(
        @field:JsonProperty("apelido")
        val nickName: String?,

        @field:JsonProperty("nome")
        val name: String?,

        @field:JsonProperty("nascimento")
        @field:JsonFormat(pattern = "yyyy-MM-dd")
        val birthDay: LocalDate,

        @field:JsonProperty("stack")
        val stack: List<String>?
)