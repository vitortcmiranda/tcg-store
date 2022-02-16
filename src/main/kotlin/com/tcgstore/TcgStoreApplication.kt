package com.tcgstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TcgStoreApplication

fun main(args: Array<String>) {
	runApplication<TcgStoreApplication>(*args)
}
