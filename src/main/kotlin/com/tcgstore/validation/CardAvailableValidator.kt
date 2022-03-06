package com.tcgstore.validation

import com.tcgstore.service.CardsService
import com.tcgstore.validation.annotations.CardAvailable
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CardAvailableValidator(private var cardService: CardsService): ConstraintValidator<CardAvailable, Set<Int>> {
    override fun isValid(value: Set<Int>?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrEmpty()){
            return false
        }
        return cardService.cardAvailable(value)
    }

}
