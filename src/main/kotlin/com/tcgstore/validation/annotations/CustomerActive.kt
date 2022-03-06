package com.tcgstore.validation.annotations

import com.tcgstore.validation.CustomerActiveValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [CustomerActiveValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)

annotation class CustomerActive(
    val message: String = "Email ja cadastrado.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
