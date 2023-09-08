package com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.advice

import com.jjeanjacques.rinhadebackend.application.service.exception.PersonNotFoundException
import jakarta.persistence.PersistenceException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.sql.SQLException
import java.time.LocalDateTime

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleExceptionInternal(ex: Exception, @Nullable body: Any?, headers: HttpHeaders, statusCode: HttpStatusCode, request: WebRequest): ResponseEntity<Any>? {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionDetailsDTO(
                title = ex.cause?.message,
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                details = ex.message,
                developerMethod = ex.javaClass.getName()
        ))
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any>? {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionDetailsDTO(
                title = "Invalid Fields Error",
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatus.BAD_REQUEST.value(),
                details = ex.message,
                developerMethod = ex.javaClass.getName()
        ))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException, request: WebRequest): ResponseEntity<Any> {
        val errorDetails = ExceptionDetailsDTO(
                title = "Validation Error",
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatus.BAD_REQUEST.value(),
                details = ex.message,
                developerMethod = ex.javaClass.name
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails)
    }

    @ExceptionHandler(PersonNotFoundException::class)
    fun handlePersonNotFoundException(ex: PersonNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val errorDetails = ExceptionDetailsDTO(
                title = "Person not found",
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatus.NOT_FOUND.value(),
                details = ex.message,
                developerMethod = ex.javaClass.name
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails)
    }

    @ExceptionHandler(value = [SQLException::class, PersistenceException::class])
    fun handleDatabaseExceptions(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        val errorDetails = ExceptionDetailsDTO(
                title = "Database Error",
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                details = ex.message,
                developerMethod = ex.javaClass.name
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails)
    }

}