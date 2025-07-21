package ru.devmark.auth.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.devmark.auth.util.JwtUtil

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val auth = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (auth != null && auth.startsWith("Bearer ")) {
            val token = auth.substring(7)
            val login = jwtUtil.extractLogin(token)
            if (login != null && !jwtUtil.isRefreshToken(token)) {
                val authentication = UsernamePasswordAuthenticationToken(login, null, emptyList())
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request, response)
    }
}
