package com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.util.*

data class PersonDto(
    val id: UUID? = null,

    @field:JsonProperty("apelido")
    val nickName: String?,

    @field:JsonProperty("nome")
    val name: String?,

    @field:JsonProperty("nascimento")
    @field:JsonFormat(pattern = "yyyy-MM-dd")
    val birthDay: LocalDate,

    val stack: List<String>?
)