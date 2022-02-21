package com.tcgstore.repository

import com.tcgstore.enums.CardStatus
import com.tcgstore.model.CardsModel
import com.tcgstore.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CardsRepository : CrudRepository<CardsModel, Int> {

    fun findByStatus(status: CardStatus): List<CardsModel>
    fun findByCustomer(customer: CustomerModel): List<CardsModel>


}