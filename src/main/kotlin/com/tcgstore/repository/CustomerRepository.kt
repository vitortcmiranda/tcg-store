package com.tcgstore.repository

import com.tcgstore.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerModel, Int> {

    fun findByNameContaining(name: String): List<CustomerModel>

    fun  findByNameStartsWith(name: String): List<CustomerModel>
    fun existsByEmail(email: String): Boolean
}