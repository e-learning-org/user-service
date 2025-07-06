package com.elearning.user_service.repositories

import com.elearning.user_service.entities.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Users, Long> {
    fun findByUsername(username: String): Users?
}
