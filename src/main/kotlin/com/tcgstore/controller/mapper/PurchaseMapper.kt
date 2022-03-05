package com.tcgstore.controller.mapper

import com.tcgstore.controller.request.PostPurchaseRequest
import com.tcgstore.model.PurchaseModel
import com.tcgstore.service.CardsService
import com.tcgstore.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val cardsService: CardsService,
    private val customerService: CustomerService
) {

    fun toModel(request: PostPurchaseRequest): PurchaseModel {
        val customer = customerService.findById(request.customerId)
        val cards = cardsService.findAllByIds(request.cardIds)

        return PurchaseModel(
            customer = customer,
            cards = cards.toMutableList(),
            price = cards.sumOf { it.price }
        )
    }
}