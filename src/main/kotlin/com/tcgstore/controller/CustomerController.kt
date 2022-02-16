package com.tcgstore.controller

import com.tcgstore.model.CustomerModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customer")
class CustomerController {
    @GetMapping
    fun getCustomer(): CustomerModel {
        return CustomerModel(
            "1",
            "Vitor",
            "email@email.com"
        )
    }
    @PostMapping
    fun createCustomer(@RequestBody customer: CustomerModel): CustomerModel {
        return customer
    }
}