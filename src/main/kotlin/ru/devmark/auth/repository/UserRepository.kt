package ru.devmark.auth.repository

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val jdbcClient: JdbcClient,
) {
}