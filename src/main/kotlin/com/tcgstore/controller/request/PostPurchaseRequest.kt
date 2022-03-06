package com.tcgstore.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import com.tcgstore.validation.annotations.CardAvailable
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PostPurchaseRequest(
    @field:NotNull
    @field:Positive
    @JsonAlias("customer_id") //camelCase -> snake_case
    val customerId: Int,

    @field:NotNull(message = "Must send the card(s) id(s).")
    @JsonAlias("card_ids")
    @CardAvailable(message = "One or more item(s) of your shopping cart is currently unavailable.")
    val cardIds: Set<Int>
)
