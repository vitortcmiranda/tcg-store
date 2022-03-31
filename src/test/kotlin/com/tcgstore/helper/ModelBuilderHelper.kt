package com.tcgstore.helper

import com.tcgstore.enums.CustomerStatus
import com.tcgstore.enums.Role
import com.tcgstore.model.CardsModel
import com.tcgstore.model.CustomerModel
import com.tcgstore.model.PurchaseModel
import java.math.BigDecimal
import java.util.*

class ModelBuilderHelper {

    fun buildCustomer(
        id: Int? = null,
        name: String = "customer name",
        email: String = "${UUID.randomUUID()}@email.com",
        password: String = "password",
        status: CustomerStatus? = null
    ) = CustomerModel(
        id = id,
        name = name,
        email = email,
        status = status ?: CustomerStatus.ATIVO,
        password = password,
        roles = setOf(Role.CUSTOMER)
    )

    fun buildPurchase(
        id: Int? = null,
        customer: CustomerModel = buildCustomer(),
        cards: MutableList<CardsModel> = mutableListOf<CardsModel>(),
        nfe: String? = UUID.randomUUID().toString(),
        price: BigDecimal = BigDecimal.TEN
    ) = PurchaseModel(
        id = id,
        customer = customer,
        cards = cards,
        nfe = nfe,
        price = price
    )
}