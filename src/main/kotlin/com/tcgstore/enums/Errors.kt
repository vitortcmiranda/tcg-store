package com.tcgstore.enums

enum class Errors(val code: String, val message: String) {
    ML101("TCGS-0001", "Card [%s] does not exists"),
    ML201("TCGS-0002", "Customer [%s] does not exists")
}