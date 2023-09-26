package com.jjeanjacques.rinhadebackend.infrastructure.rest.spring.advice

import com.jjeanjacques.rinhadebackend.application.service.exception.IllegalArgumentTypeException
import com.jjeanjacques.rinhadebackend.application.service.exception.InvalidArgumentTypeException
import com.jjeanjacques.rinhadebackend.application.service.exception.PersonNotFoundException
import jakarta.persistence.PersistenceException
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.lang.Nullable
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.sql.SQLException
import java.time.LocalDateTime

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleExceptionInternal(ex: Exception, @Nullable body: Any?, headers: HttpHeaders, statusCode: HttpStatusCode, request: WebRequest): ResponseEntity<Any>? {
        /*val body = ExceptionDetailsDTO(
            title = ex.cause?.message,
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            details = ex.message,
            developerMethod = ex.javaClass.getName()
        )*/
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any>? {
        /*val body = ExceptionDetailsDTO(
            title = "Invalid Fields Error",
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.BAD_REQUEST.value(),
            details = ex.message,
            developerMethod = ex.javaClass.getName()
        )*/
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
    }

    @ExceptionHandler(InvalidArgumentTypeException::class)
    fun handleInvalidArgumentTypeException(ex: InvalidArgumentTypeException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any>? {
        /*val body = ExceptionDetailsDTO(
            title = "Invalid Fields Error",
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.BAD_REQUEST.value(),
            details = ex.message,
            developerMethod = ex.javaClass.getName()
        )*/
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException, request: WebRequest): ResponseEntity<Any> {
        /*val errorDetails = ExceptionDetailsDTO(
                title = "Validation Error",
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
                details = ex.message,
                developerMethod = ex.javaClass.name
        )*/
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null)
    }

    @ExceptionHandler(IllegalArgumentTypeException::class)
    fun handleIllegalTypeArgumentException(ex: IllegalArgumentTypeException, request: WebRequest): ResponseEntity<Any> {
        /*val errorDetails = ExceptionDetailsDTO(
                title = "Validation Error",
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatus.BAD_REQUEST.value(),
                details = ex.message,
                developerMethod = ex.javaClass.name
        )*/
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
    }

    @ExceptionHandler(PersonNotFoundException::class)
    fun handlePersonNotFoundException(ex: PersonNotFoundException, request: WebRequest): ResponseEntity<Any> {
        /*val errorDetails = ExceptionDetailsDTO(
                title = "Person not found",
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatus.NOT_FOUND.value(),
                details = ex.message,
                developerMethod = ex.javaClass.name
        )*/
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
    }

    @Primary
    @ExceptionHandler(value = [SQLException::class, PersistenceException::class])
    fun handleDatabaseExceptions(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        /*val errorDetails = ExceptionDetailsDTO(
                title = "Database Error",
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
                details = ex.message ?: "Unknown error",
                developerMethod = ex.javaClass.name
        )*/
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null)
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any>? {
        /*val errorDetails = ExceptionDetailsDTO(
                title = "Unprocessable Entity",
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
                details = ex.message ?: "Unknown error",
                developerMethod = ex.javaClass.name
        )*/
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null)
    }

}