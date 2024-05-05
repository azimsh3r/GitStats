package com.gitstats.project.services

import com.gitstats.project.security.AuthPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient


@Service
class AuthService (@Autowired val restClient: RestClient){
    companion object {
        var token: String = ""
    }
    fun authenticate (authPrincipal: AuthPrincipal) {
        try {
            val response = restClient
                .get()
                .uri("https://api.github.com/user")
                .header("Authorization", "token ${authPrincipal.password}")
                .header("accept", "application/vnd.github.v3+json")
                .retrieve()
                .toBodilessEntity()

            token = authPrincipal.password!!
        } catch (e: Exception) {
            throw BadCredentialsException(e.message.toString())
        }
    }
}
