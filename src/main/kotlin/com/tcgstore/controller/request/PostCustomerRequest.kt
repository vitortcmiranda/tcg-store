package com.tcgstore.controller.request

import com.tcgstore.validation.annotations.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotEmpty(message = "Nome deve ser informado.")
    var name: String,

    @field:Email(message = "E-mail deve ser válido.")
    @EmailAvailable(message = "E-mail informado já utilizado.")
    var email: String
)