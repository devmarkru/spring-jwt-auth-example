package ru.devmark.auth.dto

// todo убрать, т.к. дублируется с TokenPair
data class TokenResponse(val refresh: String, val access: String)
