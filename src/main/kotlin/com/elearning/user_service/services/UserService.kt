package com.elearning.user_service.services

import com.elearning.user_service.entities.Users
import com.elearning.user_service.repositories.UserRepo
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepo) {

    fun createUser(user: Users): Users = userRepository.save(user)

    fun getAllUsers(): List<Users> = userRepository.findAll()

    fun getUserById(id: Int): Users =
        userRepository.findById(id).orElseThrow { NoSuchElementException("User not found with ID: $id") }

    fun updateUser(id: Int, updatedUser: Users): Users {
        val existingUser = getUserById(id)
        return userRepository.save(existingUser.copy(
            username = updatedUser.username,
            password = updatedUser.password
        ))
    }

    fun deleteUser(id: Int) {
        if (!userRepository.existsById(id)) {
            throw NoSuchElementException("User not found with ID: $id")
        }
        userRepository.deleteById(id)
    }
}
