package com.jjeanjacques.rinhadebackend.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate


@Entity
@Table(name = "people")
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @JsonProperty("apelido")
        @Column(length = 32, unique = true)
        val nickName: String?,

        @JsonProperty("nome")
        @Column(length = 100)
        val name: String?,

        @JsonProperty("nascimento")
        @JsonFormat(pattern = "yyyy-MM-dd")
        val birthDay: LocalDate?,

        @JsonProperty("stack")
        val stack: List<String>?
)