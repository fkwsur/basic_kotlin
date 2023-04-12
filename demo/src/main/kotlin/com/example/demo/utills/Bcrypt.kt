package com.example.demo.utills;

import org.springframework.stereotype.Service
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Service
public class Bcrypt {
    
    fun hashPassword(password: String): String {
        val encoder = BCryptPasswordEncoder()
        val hash = encoder.encode(password)
        return hash
    }

    fun compareHash(password: String, hashPassword: String): Boolean {
        val decoder = BCryptPasswordEncoder()
        return decoder.matches(password, hashPassword)
    }

}