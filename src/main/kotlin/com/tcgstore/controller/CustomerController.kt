package com.tcgstore.controller

import com.tcgstore.controller.request.PostCustomerRequest
import com.tcgstore.controller.request.PutCustomerRequest
import com.tcgstore.extension.toCustomerModel
import com.tcgstore.model.CustomerModel
import com.tcgstore.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController(
    val customerService: CustomerService
) {


    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerModel> =
        customerService.getAll(name)


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) =
        customerService.createCustomer(customer.toCustomerModel())


    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerModel =
        customerService.getById(id)


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) =
        customerService.update(customer.toCustomerModel(id))


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) =
        customerService.delete(id)


}