package com.tcgstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class TcgStoreApplication

fun main(args: Array<String>) {
	runApplication<TcgStoreApplication>(*args)
}
