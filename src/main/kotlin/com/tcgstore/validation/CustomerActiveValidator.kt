package com.tcgstore.validation

import com.tcgstore.service.CustomerService
import com.tcgstore.validation.annotations.CustomerActive
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CustomerActiveValidator( private var customerService: CustomerService): ConstraintValidator<CustomerActive, Int> {
    override fun isValid(value: Int, context: ConstraintValidatorContext?): Boolean {
        if(value <= 0 ){
            return false
        }
        return customerService.customerActive(value)
    }
}