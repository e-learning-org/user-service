package com.elearning.user_service.repositories

import com.elearning.user_service.entities.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: JpaRepository<Users,Int>  {
    fun findByUsername(username: String): Users?


}