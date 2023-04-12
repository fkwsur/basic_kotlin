package com.example.demo.utills;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
class Jwt {

    @Value("\${ACCESS_KEY}")
    lateinit var AccessKey: String

    @Value("\${REFRESH_KEY}")
    lateinit var RefreshKey: String

    private val expiredTime = 1000 * 300L
    private val rexpiredTime = 1000 * 60L * 60L * 24L

    fun CreateToken(userId: String, nickname: String): String {
        val now = Date()
        val payloads: MutableMap<String,Any> = HashMap()
        payloads["user_id"] = userId
        payloads["nickname"] = nickname
        return Jwts.builder()
            .setSubject("authorization")
            .setHeader(createHeader())
            .setClaims(payloads)
            .setExpiration(Date(now.time+expiredTime))
            .signWith(SignatureAlgorithm.HS256, AccessKey)
            .compact()
    }
    fun CreateRToken(userId: String): String {
        val now = Date()
        val payloads: MutableMap<String, Any> = HashMap()
        payloads["user_id"] = userId
        return Jwts.builder()
                .setSubject("rauthorization")
                .setHeader(createHeader())
                .setClaims(payloads)
                .setExpiration(Date(now.time + rexpiredTime))
                .signWith(SignatureAlgorithm.HS256, RefreshKey)
                .compact()
    }
    
    private fun createHeader(): Map<String, Any> {
        val header: MutableMap<String, Any> = HashMap()
        header["typ"] = "JWT"
        header["alg"] = "HS256"
        header["regDate"] = System.currentTimeMillis()
        return header
    }
    
    private fun getClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(AccessKey).parseClaimsJws(token).body
    }
    
    fun VerifyToken(token: String): String {
        return getClaims(token)["user_id"] as String
    }
    
    private fun getRClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(RefreshKey).parseClaimsJws(token).body
    }
    
    fun VerifyRToken(token: String): String {
        return getRClaims(token)["user_id"] as String
    }
}