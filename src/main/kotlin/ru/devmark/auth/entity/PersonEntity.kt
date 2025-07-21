package ru.devmark.auth.entity

import java.time.LocalDateTime

// todo класс-сущность, представляющая запись о пользователе в БД
data class PersonEntity(
    val id: Int,
    val login: String,
    val firstName: String,
    val lastName: String,
    val passwordHash: String,
    val created: LocalDateTime,
    val lastLogin: LocalDateTime,
)
