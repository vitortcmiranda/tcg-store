package com.tcgstore.service

import com.tcgstore.enums.CardStatus
import com.tcgstore.enums.Errors
import com.tcgstore.exception.NotFoundExeption
import com.tcgstore.model.CardsModel
import com.tcgstore.model.CustomerModel
import com.tcgstore.repository.CardsRepository
import org.springframework.stereotype.Service

@Service
class CardsService(
    val cardsRepository: CardsRepository,
    val customerService: CustomerService
) {
    fun create(cards: CardsModel) {
        cardsRepository.save(cards)
    }

    fun findAll(): List<CardsModel> {
        return cardsRepository.findAll().toList()
    }

    fun findActives(): List<CardsModel> {
        return cardsRepository.findByStatus(CardStatus.ATIVO)
    }

    fun findById(id: Int): CardsModel {
        return cardsRepository.findById(id).orElseThrow { NotFoundExeption(Errors.TCGS101.message.format(id),Errors.TCGS101.code) }
    }

    fun deleteById(id: Int) {
        val card = findById(id)

        card.status = CardStatus.CANCELADO

        update(card)
    }

    fun update(card: CardsModel) {
        cardsRepository.save(card)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val cards = cardsRepository.findByCustomer(customer)
        for (card in cards) {
            card.status = CardStatus.DELETADO
        }
        cardsRepository.saveAll(cards)
    }

    fun findCardsByCustomer(customerId: Int): List<CardsModel> {
        val customer = customerService.findById(customerId)

        return cardsRepository.findByCustomer(customer)
    }
}
