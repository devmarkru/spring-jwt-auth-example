package ru.devmark.auth.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.devmark.auth.repository.UserRepository
import ru.devmark.auth.util.JwtUtil
import ru.devmark.auth.dto.TokenPair

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
) {

    fun issueTokens(login: String, password: String): TokenPair {
        val user = userRepository.findByLogin(login)
            ?: throw IllegalArgumentException("Invalid credentials")
        if (!passwordEncoder.matches(password, user.passwordHash)) {
            throw IllegalArgumentException("Invalid credentials")
        }
        userRepository.updateLastLogin(user.id)
        val refresh = jwtUtil.generateRefreshToken(login)
        val access = jwtUtil.generateAccessToken(login, user.firstName, user.lastName)
        return TokenPair(refresh, access)
    }

    fun refresh(refreshToken: String): TokenPair {
        val login = jwtUtil.extractLogin(refreshToken)
            ?: throw IllegalArgumentException("Invalid refresh token")
        if (!jwtUtil.isRefreshToken(refreshToken)) {
            throw IllegalArgumentException("Invalid refresh token")
        }
        val user = userRepository.findByLogin(login)
            ?: throw IllegalArgumentException("Invalid refresh token")
        userRepository.updateLastLogin(user.id)
        val refresh = jwtUtil.generateRefreshToken(login)
        val access = jwtUtil.generateAccessToken(login, user.firstName, user.lastName)
        return TokenPair(refresh, access)
    }
}
