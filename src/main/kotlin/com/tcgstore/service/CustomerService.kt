package com.tcgstore.service

import com.tcgstore.enums.CustomerStatus
import com.tcgstore.enums.Errors
import com.tcgstore.enums.Profile
import com.tcgstore.exception.NotFoundExeption
import com.tcgstore.model.CustomerModel
import com.tcgstore.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    @org.springframework.context.annotation.Lazy
    val cardsService: CardsService
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
        val customerCopy = customer.copy(
            roles = setOf(Profile.CUSTOMER)
        )
        customerRepository.save(customerCopy)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id)
            .orElseThrow { NotFoundExeption(Errors.TCGS201.message.format(id), Errors.TCGS201.code) }
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        cardsService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean = !customerRepository.existsByEmail(email)

    fun customerActive(customerId: Int): Boolean {
        val customer = customerRepository.findById(customerId)
        var customerAtivo : Boolean = true
        customer.map {  customerAtivo = (it.status == CustomerStatus.ATIVO) }
        return customerAtivo
    }

}