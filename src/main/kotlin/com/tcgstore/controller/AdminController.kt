package com.tcgstore.controller

import com.tcgstore.security.annotations.AdminOnly
import com.tcgstore.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin")
class AdminController(
    val customerService: CustomerService
) {


    @GetMapping("/report")
    @AdminOnly
    fun getAll(): String =
        "This is a Report. Only admin can see it"

}