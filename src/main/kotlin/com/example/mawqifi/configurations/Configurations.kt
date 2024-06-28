package com.example.mawqifi.configurations

import com.example.mawqifi.features.token.service.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class Configurations {

    @Bean
    fun userDetailsService(): UserDetailsService = CustomUserDetailsService()

    @Bean
    fun encoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
        configuration.authenticationManager

    @Bean
    fun authenticationProvider(): AuthenticationProvider = DaoAuthenticationProvider()
        .also {
            it.setUserDetailsService(userDetailsService())
            it.setPasswordEncoder(encoder())
        }
}