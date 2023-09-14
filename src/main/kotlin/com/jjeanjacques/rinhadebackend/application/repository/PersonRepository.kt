package com.jjeanjacques.rinhadebackend.application.repository

import com.jjeanjacques.rinhadebackend.domain.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonRepository : JpaRepository<Person, UUID> {
    @Query("SELECT p FROM Person p WHERE " +
            "p.name LIKE :term OR " +
            "p.nickName LIKE :term OR " +
            "EXISTS (SELECT s FROM p.stack s WHERE s LIKE :term)")
    fun searchByTerm(term: String): List<Person>
}