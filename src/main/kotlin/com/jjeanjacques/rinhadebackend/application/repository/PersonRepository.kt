package com.jjeanjacques.rinhadebackend.application.repository

import com.jjeanjacques.rinhadebackend.domain.Person
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import java.util.*

@Repository
interface PersonRepository : JpaRepository<Person, UUID> {
    @Query(
        "SELECT p FROM Person p WHERE " +
                "p.name LIKE :term OR " +
                "p.nickName LIKE :term OR " +
                "p.stack LIKE :term"
    )
    fun searchByTerm(term: String, pageable: Pageable): List<Person>
}