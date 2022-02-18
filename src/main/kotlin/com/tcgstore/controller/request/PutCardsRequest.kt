package com.tcgstore.controller.request

import java.math.BigDecimal

data class PutCardsRequest(
    var name: String? = null,

    var price: BigDecimal? = null,

    var description: String? = null,

)