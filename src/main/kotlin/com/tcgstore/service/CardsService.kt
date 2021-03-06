package com.tcgstore.service

import com.tcgstore.controller.response.CardsResponse
import com.tcgstore.enums.CardStatus
import com.tcgstore.enums.Errors
import com.tcgstore.exception.NotFoundExeption
import com.tcgstore.extension.toResponse
import com.tcgstore.model.CardsModel
import com.tcgstore.model.CustomerModel
import com.tcgstore.repository.CardsRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CardsService(
    private val cardsRepository: CardsRepository,
    private val customerService: CustomerService
) {
    fun create(cards: CardsModel) {
        cardsRepository.save(cards)
    }

    fun findAll(pageable: Pageable): Page<CardsResponse> {
        return cardsRepository.findAll(pageable).map { it.toResponse() }
    }

    fun findActives(pageable: Pageable): Page<CardsModel> {
        return cardsRepository.findByStatus(CardStatus.ATIVO, pageable)
    }

    fun findById(id: Int): CardsModel {
        return cardsRepository.findById(id)
            .orElseThrow { NotFoundExeption(Errors.TCGS101.message.format(id), Errors.TCGS101.code) }
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

    fun findAllByIds(cardIds: Set<Int>): List<CardsModel> {
        return cardsRepository.findAllById(cardIds).toList()

    }

    fun purchase(cards: MutableList<CardsModel>) {
        cards.map {
            it.status = CardStatus.VENDIDO
        }
        cardsRepository.saveAll(cards)
    }

    fun cardAvailable(cardsIds: Set<Int>): Boolean {
        val cards = findAllByIds(cardsIds)
        cards.map {
            if(it.status == CardStatus.VENDIDO){
                return false
            }
        }
        return true
    }
}
