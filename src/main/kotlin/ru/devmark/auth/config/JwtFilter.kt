package ru.devmark.auth.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.filter.OncePerRequestFilter
import ru.devmark.auth.util.JwtUtil

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val auth = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (auth != null && auth.startsWith(BEARER_PREFIX)) {
            val token = auth.substring(BEARER_PREFIX.length)
            val login = jwtUtil.extractLogin(token)
            if (login != null && !jwtUtil.isRefreshToken(token)) {
                val userDetails = userDetailsService.loadUserByUsername(login)
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request, response)
    }

    private companion object {
        private const val BEARER_PREFIX = "Bearer "
    }
}
