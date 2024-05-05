package com.gitstats.project.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails (private val authPrincipal: AuthPrincipal) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = null!!

    override fun getPassword(): String = authPrincipal.password!!

    override fun getUsername(): String = authPrincipal.username!!

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}