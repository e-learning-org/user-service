package com.elearning.user_service.services

import com.elearning.user_service.entities.UserPrincipal
import com.elearning.user_service.entities.Users
import com.elearning.user_service.repositories.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService(@Autowired val userRepo: UserRepo): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        val user = userRepo.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found").also {
                println("User not found")
            }
        return UserPrincipal(user)



    }

}