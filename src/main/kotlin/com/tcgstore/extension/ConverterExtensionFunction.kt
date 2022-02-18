package com.tcgstore.extension

import com.tcgstore.controller.request.PostCardsRequest
import com.tcgstore.controller.request.PostCustomerRequest
import com.tcgstore.controller.request.PutCardsRequest
import com.tcgstore.controller.request.PutCustomerRequest
import com.tcgstore.enum.CardStatus
import com.tcgstore.model.CardsModel
import com.tcgstore.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: Int): CustomerModel {
    return CustomerModel(id = id, name = this.name, email = this.email)
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
