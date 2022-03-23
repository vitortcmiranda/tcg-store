package com.tcgstore.repository

import com.tcgstore.enums.CardStatus
import com.tcgstore.model.CardsModel
import com.tcgstore.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CardsRepository : JpaRepository<CardsModel, Int> {

    fun findByStatus(status: CardStatus, pageable: Pageable): Page<CardsModel>
    fun findByCustomer(customer: CustomerModel): List<CardsModel>

//    fun findAll(pageable: Pageable): Page<CardsModel>


}