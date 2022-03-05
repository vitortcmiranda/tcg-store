package com.tcgstore.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PostPurchaseRequest(
    @field:NotNull
    @field:Positive
    @JsonAlias("customer_id") //camelCase -> snake_case
    val customerId: Int,

    @field:NotNull
    @JsonAlias("card_ids")
    val cardIds: Set<Int>
)
