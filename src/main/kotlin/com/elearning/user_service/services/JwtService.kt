package com.elearning.user_service.services

import com.elearning.user_service.entities.Users
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component

@Component
class JwtService {
    private val secret = "mysecretkey1234567890andsomelongertextsothatwecanensurethatitwillbeworkingjustfineyouknowwhatImean"

    fun generateToken(user: Users): String {
        return Jwts.builder()
            .setSubject(user.username)
            .claim("role", user.role)
            .signWith(Keys.hmacShaKeyFor(secret.toByteArray()), SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(secret.toByteArray())
            .build()
            .parseClaimsJws(token)
            .body
}
