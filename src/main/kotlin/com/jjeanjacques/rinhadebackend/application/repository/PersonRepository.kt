package com.jjeanjacques.rinhadebackend.application.repository

import com.jjeanjacques.rinhadebackend.domain.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long>