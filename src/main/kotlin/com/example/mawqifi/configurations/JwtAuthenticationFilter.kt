package com.example.mawqifi.configurations

import com.example.mawqifi.common.Helper.Companion.doesNotContainBearerToken
import com.example.mawqifi.common.Helper.Companion.extractTokenValue
import com.example.mawqifi.features.token.service.CustomUserDetailsService
import com.example.mawqifi.features.token.service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    lateinit var userDetailsService: CustomUserDetailsService

    @Autowired
    lateinit var tokenService: TokenService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")

        if (authHeader.doesNotContainBearerToken()) {
            filterChain.doFilter(request, response)
            return
        }

        val jwtToken = authHeader!!.extractTokenValue()
        val email = tokenService.extractEmail(jwtToken)

        if (email != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(email)

            if (tokenService.isValid(jwtToken, userDetails)) {
                updateContextHolder(userDetails, request)
            }

            filterChain.doFilter(request, response)
        }
    }

    private fun updateContextHolder(userDetails: UserDetails, request: HttpServletRequest) {
        val authenticated =
            UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.authorities)
        authenticated.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authenticated
    }
}