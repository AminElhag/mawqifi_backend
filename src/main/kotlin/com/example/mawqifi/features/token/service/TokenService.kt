package com.example.mawqifi.features.token.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService {

    @Value("\${token.key.secret}")
    private val secret: String = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629"

    private val securityKey = Keys.hmacShaKeyFor(
        secret.toByteArray()
    )

    fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionsClaims: Map<String, Any> = emptyMap()
    ): String = Jwts.builder()
        .claims()
        .subject(userDetails.username)
        .issuedAt(Date(System.currentTimeMillis()))
        .expiration(expirationDate)
        .add(additionsClaims)
        .and()
        .signWith(securityKey)
        .compact()

    private fun getAllClaims(token: String): Claims {
        return Jwts.parser().verifyWith(securityKey).build().parseSignedClaims(token).payload
    }


    private fun isExpired(token: String): Boolean =
        getAllClaims(token).expiration.before(Date(System.currentTimeMillis()))

    fun isValid(token: String, userDetails: UserDetails): Boolean =
        extractEmail(token) == userDetails.username && !isExpired(token)

    fun extractEmail(token: String): String? = getAllClaims(token).subject
}