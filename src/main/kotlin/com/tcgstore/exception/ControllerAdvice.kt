package com.tcgstore.exception

import com.tcgstore.controller.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@org.springframework.web.bind.annotation.ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val erro = ErrorResponse(
            httpCode = 400,
            message = "Este recurso n existe",
            internalCode = "0001",
            errors = null
        )
        return ResponseEntity(erro,HttpStatus.BAD_REQUEST)
    }
}