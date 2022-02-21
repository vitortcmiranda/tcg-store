package com.tcgstore.extension

import com.tcgstore.controller.request.PostCardsRequest
import com.tcgstore.controller.request.PostCustomerRequest
import com.tcgstore.controller.request.PutCardsRequest
import com.tcgstore.controller.request.PutCustomerRequest
import com.tcgstore.enums.CardStatus
import com.tcgstore.enums.CustomerStatus
import com.tcgstore.model.CardsModel
import com.tcgstore.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(id = previousValue.id, name = this.name, email = this.email, status = previousValue.status)
}

fun PostCardsRequest.toCardsModel(customer: CustomerModel): CardsModel {
    return CardsModel(
        name = this.name,
        price = this.price,
        status = CardStatus.ATIVO,
        description = this.description,
        customer = customer
    )
}

fun PutCardsRequest.toCardsModel(previousCard: CardsModel): CardsModel {
    return CardsModel(
        id = previousCard.id,
        name = this.name ?: previousCard.name,
        description = this.description ?: previousCard.description,
        price = previousCard.price
    )

}
