package com.tcgstore.repository

import com.tcgstore.model.CustomerModel
import com.tcgstore.model.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<PurchaseModel, Int> {
    fun findByCustomer(findById: CustomerModel): List<PurchaseModel>


}
