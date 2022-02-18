package com.tcgstore.service

import com.tcgstore.enum.CardStatus
import com.tcgstore.model.CardsModel
import com.tcgstore.repository.CardsRepository
import org.springframework.stereotype.Service

@Service
class CardsService(
    val cardsRepository: CardsRepository
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
        return cardsRepository.findById(id).orElseThrow()
    }


}
