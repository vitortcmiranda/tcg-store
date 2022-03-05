package com.tcgstore.events.listener

import com.tcgstore.events.PurchaseEvent
import com.tcgstore.service.CardsService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class UpdateSoldCardListener(
    private val cardsService: CardsService
) {
    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        println("Gerando Nfe")
        cardsService.purchase(purchaseEvent.purchaseModel.cards)
    }
}