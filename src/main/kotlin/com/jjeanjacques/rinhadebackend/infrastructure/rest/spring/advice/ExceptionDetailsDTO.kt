package com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.advice

data class ExceptionDetailsDTO(
        var title: String? = null,
        val status: Int? = null,
        val details: String? = null,
        val timestamp: String? = null,
        val developerMethod: String? = null,
)
