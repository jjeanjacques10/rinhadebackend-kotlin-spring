package com.jjeanjacques.rinhadebackend.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.util.*


@Entity
@Table(name = "people")
data class Person(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID? = null,

    @JsonProperty("apelido")
    @Column(length = 32, unique = true)
    val nickName: String?,

    @JsonProperty("nome")
    @Column(length = 100)
    val name: String?,

    @JsonProperty("nascimento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    val birthDay: LocalDate,

    @Column(length = 255)
    var stack: String? = null
) {
    constructor() : this(null, null, null, LocalDate.now(), null)
}
