package ru.devmark.auth.dto

data class TokenResponse(val refresh: String, val access: String)
