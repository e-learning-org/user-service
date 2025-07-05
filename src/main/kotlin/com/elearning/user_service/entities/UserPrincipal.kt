package com.elearning.user_service.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(val user: Users): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return listOf(SimpleGrantedAuthority("ROLE_${user.role.uppercase()}"))

    }

    override fun getPassword(): String? {
        return user.password
    }

    override fun getUsername(): String? {
       return user.username
    }

}