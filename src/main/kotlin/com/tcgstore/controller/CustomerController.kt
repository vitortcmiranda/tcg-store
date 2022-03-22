package com.tcgstore.controller

import com.tcgstore.controller.request.PostCustomerRequest
import com.tcgstore.controller.request.PutCustomerRequest
import com.tcgstore.controller.response.CustomerResponse
import com.tcgstore.extension.toCustomerModel
import com.tcgstore.extension.toResponse
import com.tcgstore.security.annotations.AdminOnly
import com.tcgstore.security.annotations.UserCanOnlyAccessTheirOwnResource
import com.tcgstore.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("customer")
class CustomerController(
    val customerService: CustomerService
) {


    @GetMapping
    @AdminOnly
    fun getAll(@RequestParam name: String?): List<CustomerResponse> =
        customerService.getAll(name).map { it.toResponse() }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody @Validated customer: PostCustomerRequest) =
        customerService.createCustomer(customer.toCustomerModel())


    @GetMapping("/{id}")
    @UserCanOnlyAccessTheirOwnResource
    fun getCustomer(@PathVariable id: Int): CustomerResponse =
        customerService.findById(id).toResponse()


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UserCanOnlyAccessTheirOwnResource
    fun update(@PathVariable id: Int, @Valid @RequestBody customer: PutCustomerRequest) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @AdminOnly
    fun delete(@PathVariable id: Int) =
        customerService.delete(id)


}