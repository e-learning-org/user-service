package com.elearning.user_service.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig (
    @Autowired
    private val userDetailsService: UserDetailsService,

) {
    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain  {
    return http.csrf { it.disable() }
        .authorizeHttpRequests { it
            .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
            .anyRequest().authenticated() }
        .httpBasic(Customizer.withDefaults())
        .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .build()

    }

    // Example of in-memory user details (commented out in original Java code)
    //
    // @Bean
    // fun userDetailsService(): UserDetailsService {
    //     val user1 = User
    //         .withDefaultPasswordEncoder()
    //         .username("kiran")
    //         .password("k@123")
    //         .roles("USER")
    //         .build()
    //
    //     val user2 = User
    //         .withDefaultPasswordEncoder()
    //         .username("harsh")
    //         .password("h@123")
    //         .roles("ADMIN")
    //         .build()
    //
    //     return InMemoryUserDetailsManager(user1, user2)
    // }


    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance())
        provider.setUserDetailsService(userDetailsService)
        return provider
    }

}