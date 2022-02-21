package com.tcgstore.exception

class NotFoundExeption(override val message: String, val errorCode: String) : Exception() {
}