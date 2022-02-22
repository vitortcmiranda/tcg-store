package com.tcgstore.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PostCardsRequest(
    @field:NotEmpty(message = "Nome deve ser informado.")
    var name: String,

    @field:NotEmpty(message = "Preço deve ser informado.")
    var price: BigDecimal,

    @field:NotNull(message = "Descrição deve ser informada.")
    var description: String,

    @field:NotNull
    @JsonAlias("customer_id")
    var customerId: Int
)
