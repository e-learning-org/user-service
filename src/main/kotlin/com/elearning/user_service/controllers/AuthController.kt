package com.elearning.user_service.controllers

import com.elearning.user_service.entities.Users
import com.elearning.user_service.repositories.UserRepository
import com.elearning.user_service.services.JwtService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users/auth")
class AuthController(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
    val jwtService: JwtService
) {
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    fun register(@RequestBody req: RegisterRequest): ResponseEntity<String> {
        val user = Users(
            username = req.username,
            password = passwordEncoder.encode(req.password),
            role = req.role
        )
        userRepository.save(user)
        return ResponseEntity.ok("User registered")
    }

    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): ResponseEntity<Map<String, String>> {
        val user = userRepository.findByUsername(req.username)
            ?: return ResponseEntity.status(401).build()

        if (passwordEncoder.matches(req.password, user.password)) {
            val token = jwtService.generateToken(user)
            return ResponseEntity.ok(mapOf("token" to token))
        }
        return ResponseEntity.status(401).build()
    }
}

data class RegisterRequest(val username: String, val password: String, val role: String)
data class LoginRequest(val username: String, val password: String)