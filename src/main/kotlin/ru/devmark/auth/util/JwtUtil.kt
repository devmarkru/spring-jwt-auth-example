package ru.devmark.auth.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import ru.devmark.auth.config.JwtProperties
import java.time.Instant
import java.util.Date

@Component
class JwtUtil(
    private val props: JwtProperties,
) {
    private val key = Keys.hmacShaKeyFor(props.secret.toByteArray())

    fun generateAccessToken(login: String, firstName: String, lastName: String): String =
        Jwts.builder()
            .subject(login)
            .claim("type", "access")
            .claim("firstName", firstName)
            .claim("lastName", lastName)
            .expiration(Date.from(Instant.now().plusSeconds(props.accessTtl)))
            .signWith(key)
            .compact()

    fun generateRefreshToken(login: String): String =
        Jwts.builder()
            .subject(login)
            .claim("type", "refresh")
            .expiration(Date.from(Instant.now().plusSeconds(props.refreshTtl)))
            .signWith(key)
            .compact()

    fun extractLogin(token: String): String? = runCatching {
        Jwts.parser().verifyWith(key).build().parseSignedClaims(token).payload.subject
    }.getOrNull()

    fun isRefreshToken(token: String): Boolean = runCatching {
        val claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).payload
        claims["type"] == "refresh"
    }.getOrDefault(false)
}
