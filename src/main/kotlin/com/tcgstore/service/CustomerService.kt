package com.tcgstore.service

import com.tcgstore.model.CustomerModel
import com.tcgstore.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {
    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            if (it.get(0).isUpperCase()) {
                return customerRepository.findByNameStartsWith(it).toList()
            }
            return customerRepository.findByNameContaining(it).toList()
        }
        return customerRepository.findAll().toList()
    }

    fun createCustomer(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
       val customer = findById(id)
        customerRepository.deleteById(id)
    }
}