package com.elearning.user_service.controllers

import com.elearning.user_service.entities.Users
import com.elearning.user_service.services.UserService
import org.springframework.http.HttpStatus
//import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('USER')")  // only admins can view all users
    fun createUser(@RequestBody user: Users): Users = userService.createUser(user)

    @GetMapping
    fun getAllUsers(): List<Users> = userService.getAllUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Int): Users = userService.getUserById(id)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody user: Users): Users =
        userService.updateUser(id, user)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Int) = userService.deleteUser(id)

    @GetMapping("test")
    fun test(): String = "Hello World!"
}
