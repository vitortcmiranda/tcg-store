package com.tcgstore.service

import com.tcgstore.events.PurchaseEvent
import com.tcgstore.model.PurchaseModel
import com.tcgstore.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val customerService: CustomerService,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun create(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
        println("Disparando evento de compra")
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
        println("Finalização do processamento!")
    }

    fun update(purchaseModel: PurchaseModel) {
        println("Atualizando status do livro")
        purchaseRepository.save(purchaseModel)
    }

    fun findPurchasesByUserID(id: Int): List<PurchaseModel> =
        purchaseRepository.findByCustomer(customerService.findById(id))


}
