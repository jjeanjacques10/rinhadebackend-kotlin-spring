package com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class PersonDto(
        @JsonProperty("apelido")
        @field:NotNull(message = "Apelido nao deve ser nulo")
        val nickName: String?,

        @JsonProperty("nome")
        @field:NotNull(message = "Nome nao deve ser nulo")
        @field:NotBlank(message = "Name is required")
        val name: String?,

        @JsonProperty("nascimento")
        @field:JsonFormat(pattern = "yyyy-MM-dd")
        @field:NotNull(message = "Nascimento nao deve ser nulo")
        val birthDay: LocalDate?,

        @JsonProperty("stack")
        val stack: List<String>?
)