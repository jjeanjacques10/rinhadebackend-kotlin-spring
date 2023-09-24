package com.jjeanjacques.rinhadebackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class RinhadebackendApplication

fun main(args: Array<String>) {
    runApplication<RinhadebackendApplication>(*args)
}
