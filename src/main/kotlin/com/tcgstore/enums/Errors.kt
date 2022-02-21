package com.tcgstore.enums

enum class Errors(val code: String, val message: String) {
    ML101("TCGS-101", "Card [%s] does not exists"),
    ML102("TCGS-102", "Cannot update card with status [%s]"),
    ML201("TCGS-202", "Customer [%s] does not exists")
}