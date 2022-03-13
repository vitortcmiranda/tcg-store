package com.tcgstore.controller.request

data class LoginRequest(
    val email: String,
    val password: String,
)