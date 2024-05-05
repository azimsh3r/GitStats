package com.gitstats.project.security

import com.gitstats.project.exceptionHandling.BadCredentialsException
import com.gitstats.project.services.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthProvider : AuthenticationProvider {

    @Autowired
    private lateinit var authService: AuthService

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password: String = authentication.credentials.toString()

        // Authenticate the user using the provided credentials
        val authPrincipal = AuthPrincipal()
        authPrincipal.username = username
        authPrincipal.password = password
        try {
            authService.authenticate(authPrincipal)
        } catch (e: BadCredentialsException) {
            throw BadCredentialsException("Authentication failed ${e.message}")
        }
        // If authentication is successful, create and return an Authentication object
        return UsernamePasswordAuthenticationToken(authentication.principal, authentication.credentials, emptyList())
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}