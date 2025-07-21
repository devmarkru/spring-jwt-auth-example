package ru.devmark.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import ru.devmark.auth.repository.UserRepository

@Configuration
class UserDetailsServiceConfig(
    private val userRepository: UserRepository,
) {

    @Bean
    fun userDetailsService(): UserDetailsService = UserDetailsService { username ->
        val user = userRepository.findByLogin(username)
            ?: throw UsernameNotFoundException("User not found")
        User.withUsername(user.login)
            .password(user.passwordHash)
            .authorities(listOf(SimpleGrantedAuthority("ROLE_" + user.role.uppercase())))
            .build()
    }
}
