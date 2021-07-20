package com.colutti.starwars.actors.advice

import com.colutti.starwars.actors.dto.exception.ErrorMessage
import com.colutti.starwars.actors.exception.ActorNotFoundException
import com.colutti.starwars.actors.exception.PersonageNotFoundException
import com.colutti.starwars.actors.exception.RequestClientException
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(JsonParseException::class)
    fun JsonFormatExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception):
            ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage("Error", exception.message ?: "error"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ActorNotFoundException::class)
    fun ActorNotFoundExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception):
            ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Not Found", exception.message ?: "error"), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(PersonageNotFoundException::class)
    fun PersonageNotFoundExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception):
            ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Not Found", exception.message ?: "error"), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(RequestClientException::class)
    fun RequestClientExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception):
            ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Problem communicating with other services", exception.message ?: "error"), HttpStatus.SERVICE_UNAVAILABLE)
    }

}