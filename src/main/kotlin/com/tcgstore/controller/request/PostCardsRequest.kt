package com.tcgstore.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal

data class PostCardsRequest(
    var name: String,

    var price: BigDecimal,

    var description: String,

    @JsonAlias("customer_id")
    var customerId: Int
)
