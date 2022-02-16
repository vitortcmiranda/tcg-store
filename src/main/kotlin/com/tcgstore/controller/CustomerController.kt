package com.tcgstore.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class CustomerController {
    @GetMapping("/id")
    fun helloWorld(): String{
        return "Customer 1"
    }
    @GetMapping("/all")
    fun getAll(): String {
        return "Client 1 , Client 2"
    }
}