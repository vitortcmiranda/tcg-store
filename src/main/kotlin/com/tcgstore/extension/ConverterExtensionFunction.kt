package com.tcgstore.extension

import com.tcgstore.controller.request.PostCardsRequest
import com.tcgstore.controller.request.PostCustomerRequest
import com.tcgstore.controller.request.PutCardsRequest
import com.tcgstore.controller.request.PutCustomerRequest
import com.tcgstore.controller.response.CardsResponse
import com.tcgstore.controller.response.CustomerResponse
import com.tcgstore.enums.CardStatus
import com.tcgstore.enums.CustomerStatus
import com.tcgstore.model.CardsModel
import com.tcgstore.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(
        name = this.name,
        email = this.email,
        status = CustomerStatus.ATIVO,
        password = this.password
    )
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(
        id = previousValue.id,
        name = this.name,
        email = this.email,
        status = previousValue.status,
        password = previousValue.password
    )
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

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun CardsModel.toResponse(): CardsResponse {
    return CardsResponse(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price,
        customer = this.customer,
        status = this.status

    )
}