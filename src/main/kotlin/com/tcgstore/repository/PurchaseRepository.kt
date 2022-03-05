package com.tcgstore.repository

import com.tcgstore.model.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<PurchaseModel,Int>{

}
