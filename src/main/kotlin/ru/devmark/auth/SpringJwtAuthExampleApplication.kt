package ru.devmark.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import ru.devmark.auth.config.JwtConfig

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig::class)
class SpringJwtAuthExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringJwtAuthExampleApplication>(*args)
}
