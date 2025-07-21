package ru.devmark.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtConfig(
    val secret: String,
    val refreshLifetimeSec: Long,
    val accessLifetimeSec: Long,
)
