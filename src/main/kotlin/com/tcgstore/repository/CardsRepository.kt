package com.tcgstore.repository

import com.tcgstore.enums.CardStatus
import com.tcgstore.model.CardsModel
import org.springframework.data.repository.CrudRepository

interface CardsRepository : CrudRepository<CardsModel, Int> {

    fun findByStatus(status: CardStatus): List<CardsModel>


}