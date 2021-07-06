package com.colutti.starwars.actors

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ActorsApplication

fun main(args: Array<String>) {
	runApplication<ActorsApplication>(*args)
}
