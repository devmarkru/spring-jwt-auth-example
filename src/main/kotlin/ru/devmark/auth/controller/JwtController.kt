package ru.devmark.auth.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.devmark.auth.service.AuthService
import ru.devmark.auth.dto.LoginRequest
import ru.devmark.auth.dto.RefreshRequest
import ru.devmark.auth.dto.TokenResponse

@RestController
@RequestMapping("/jwt")
class JwtController(
    private val authService: AuthService,
) {

    @PostMapping
    fun token(@RequestBody request: LoginRequest): TokenResponse {
        val pair = authService.issueTokens(request.login, request.password)
        return TokenResponse(pair.refresh, pair.access)
    }

    @PutMapping
    fun refresh(@RequestBody request: RefreshRequest): TokenResponse {
        val pair = authService.refresh(request.refresh)
        return TokenResponse(pair.refresh, pair.access)
    }
}
