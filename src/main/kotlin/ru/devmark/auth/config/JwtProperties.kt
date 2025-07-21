package ru.devmark.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.DefaultValue
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    var secret: String = "",
    var refreshTtl: Long = 0,
    var accessTtl: Long = 300,
)
