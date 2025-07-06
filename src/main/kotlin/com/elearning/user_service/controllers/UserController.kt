package com.elearning.user_service.controllers

import com.elearning.user_service.entities.Users
import com.elearning.user_service.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.elearning.user_service.clients.CoursesClient
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping("/users")
class UserController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    @Autowired
    private val courseClient: CoursesClient
) {

    @GetMapping("/my-courses")
    fun getMyCourses(): ResponseEntity<Any>{
        val username = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findByUsername(username)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        return ResponseEntity.ok(courseClient.getCoursesByUserId(user.id))

    }
    @GetMapping("/my-courses/exception")
    @CircuitBreaker(name = "userService", fallbackMethod = "fallback")
    fun getMyCoursesWithException(): ResponseEntity<Any>{
        val username = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findByUsername(username)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        return ResponseEntity.ok(courseClient.getCoursesByUserIdWithException(user.id))
    }
    fun fallback(ex: Throwable): ResponseEntity<Any> {
        println("Fallback triggered: ${ex.message}")
        return ResponseEntity.ok("Fault Tolerance is fulfilled successfully")
    }

    @GetMapping("auth/log")
    fun test() = "Hello World!"

}
