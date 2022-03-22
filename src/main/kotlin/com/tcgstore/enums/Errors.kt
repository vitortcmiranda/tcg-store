package com.tcgstore.enums

enum class Errors(val code: String, val message: String) {

    TCGS000("TCGS-000","Access denied"),
    TCGS001("TCGS-001","Invalid request"),
    TCGS101("TCGS-101", "Card [%s] does not exists"),
    TCGS102("TCGS-102", "Cannot update card with status [%s]"),
    TCGS201("TCGS-202", "Customer [%s] does not exists")
}