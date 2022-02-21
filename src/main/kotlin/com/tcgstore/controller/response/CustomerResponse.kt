package com.tcgstore.controller.response

import com.tcgstore.enums.CustomerStatus

data class CustomerResponse(
    var id: Int? = null,

    var name: String,

    var email: String,

    var status: CustomerStatus
)
