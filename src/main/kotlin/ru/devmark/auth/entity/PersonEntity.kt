package ru.devmark.auth.entity

import java.time.LocalDateTime

data class PersonEntity(
    val id: Int,
    val login: String,
    val firstName: String,
    val lastName: String,
    val role: String,
    val passwordHash: String,
    val created: LocalDateTime,
    val lastLogin: LocalDateTime,
)
