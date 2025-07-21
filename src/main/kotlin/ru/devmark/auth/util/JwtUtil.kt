package ru.devmark.auth.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Date

@Component
class JwtUtil(
    @Value("\${jwt.secret}")
    secret: String,
    @Value("\${jwt.refresh-ttl}")
    private val refreshTtl: Long,
) {
    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateAccessToken(login: String, firstName: String, lastName: String): String =
        Jwts.builder()
            .subject(login)
            .claim("type", "access")
            .claim("firstName", firstName)
            .claim("lastName", lastName)
            .expiration(Date.from(Instant.now().plusSeconds(300)))
            .signWith(key)
            .compact()

    fun generateRefreshToken(login: String): String =
        Jwts.builder()
            .subject(login)
            .claim("type", "refresh")
            .expiration(Date.from(Instant.now().plusSeconds(refreshTtl)))
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
