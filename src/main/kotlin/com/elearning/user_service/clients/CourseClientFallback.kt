//package com.elearning.user_service.clients
//
//import org.springframework.cloud.openfeign.FallbackFactory
//import org.springframework.stereotype.Component
//
//@Component
//class CourseClientFallback: FallbackFactory<CoursesClient> {
//    override fun create(cause: Throwable): CoursesClient {
//        return object : CoursesClient {
//            override fun getCoursesByUserId(userId: Int): List<Course> {
//                println("Fallback triggered due to: ${cause.message}")
//                return emptyList() // or return cached/static data
//            }
//
//            override fun getCoursesByUserIdWithException(userId: Int): String {
//                println("Course service is down. Returning fancy string")
//                return "FANCY STRING"
//            }
//        }
//    }
////    override fun getCoursesByUserId(userId: Int): List<Course> {
////        println("Course service is down. Returning empty list.")
////        return emptyList()
////    }
////
////    override fun getCoursesByUserIdWithException(userId: Int): String {
////        println("Course service is down. Returning fancy string")
////        return "FANCY STRING"
////
////    }
//}