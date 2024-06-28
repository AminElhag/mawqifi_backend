package com.example.mawqifi.features.token.service

import com.example.mawqifi.features.auth.repository.AuthRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService: UserDetailsService {

    @Autowired
    lateinit var authRepository: AuthRepository

    override fun loadUserByUsername(username: String): UserDetails {
        return authRepository.findByPhoneNumber(username)?.toUserDetails()?:throw UsernameNotFoundException(username)
    }
}