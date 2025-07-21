package ru.devmark.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringJwtAuthExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringJwtAuthExampleApplication>(*args)
}
