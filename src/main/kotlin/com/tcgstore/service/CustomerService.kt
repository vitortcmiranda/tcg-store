package com.tcgstore.service

import com.tcgstore.model.CustomerModel
import com.tcgstore.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {
    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    fun createCustomer(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun getCustomer(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun update(customer: CustomerModel) {
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        if(!customerRepository.existsById(id!!)){
            throw Exception()
        }
        customerRepository.deleteById(id)
    }
}