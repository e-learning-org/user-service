package com.elearning.user_service.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

data class Course(val id: Int, val name: String,val status: String, val userIds: List<Int>)

@FeignClient(name="course-service")
interface CoursesClient {
    @GetMapping("/courses/user/{userId}")
    fun getCoursesByUserId(@PathVariable userId: Int): List<Course>
    @GetMapping("/courses/user/{userId}/exception")
    fun getCoursesByUserIdWithException(@PathVariable userId: Int): String
}