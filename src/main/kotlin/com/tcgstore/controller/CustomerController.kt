package com.tcgstore.controller

import com.tcgstore.controller.request.PostCustomerRequest
import com.tcgstore.model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController {

    val customers = mutableListOf<CustomerModel>()

    @GetMapping
    fun getCustomer(): List<CustomerModel> {
        return customers;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
        val id = if(customers.isEmpty()){
            1
        }else{
            customers.last().id.toInt() + 1
        }
        customers.add(CustomerModel(id.toString(),customer.name,customer.email))
    }
}