package com.tcgstore.controller.response

import com.tcgstore.enums.CardStatus
import com.tcgstore.model.CustomerModel
import java.math.BigDecimal

data class CardsResponse(

    var id: Int? = null,

    var name: String,

    var description: String,

    var price: BigDecimal,

    var customer: CustomerModel? = null,

    var status: CardStatus? = null
)