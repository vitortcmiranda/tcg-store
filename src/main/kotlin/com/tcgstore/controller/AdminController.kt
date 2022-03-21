package com.tcgstore.controller

import com.tcgstore.controller.request.PostCustomerRequest
import com.tcgstore.controller.request.PutCustomerRequest
import com.tcgstore.controller.response.CustomerResponse
import com.tcgstore.extension.toCustomerModel
import com.tcgstore.extension.toResponse
import com.tcgstore.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("admin")
class AdminController(
    val customerService: CustomerService
) {


    @GetMapping("/report")
    fun getAll(): String =
        "This is a Report. Only admin can see it"

}