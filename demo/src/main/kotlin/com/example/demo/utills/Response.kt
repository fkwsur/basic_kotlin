package com.example.demo.utills;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.*;

@Service
class Response {
	
		//단순 성공 핸들러
    fun SuccessReply(): ResponseEntity<Any> {
        val map = mapOf("result" to "successs")
        return ResponseEntity(map, HttpStatus.OK)
    }

		//단순 실패
    fun FailReply(): ResponseEntity<Any> {
        val map = mapOf("result" to "failed")
        return ResponseEntity(map,HttpStatus.OK)
    }
		// 모든 응답반환
    fun Reply(obj:Any): ResponseEntity<Any> {
        return ResponseEntity(obj, HttpStatus.OK)
    }
		// msg를 보낼 때
    fun Notice(msg: String): ResponseEntity<Any> {
        val map = mapOf("msg" to msg)
        return ResponseEntity(map, HttpStatus.OK)
    }
		// 에러 핸들러
    @ExceptionHandler(Exception::class)
    fun Ereply(e: Exception): ResponseEntity<Any> {
        val message = e.message
        if (message != null && message.contains("JWT expired")) {
            val map = mapOf("expired" to true)
            return ResponseEntity(map, HttpStatus.OK)
        }
        val map = mapOf("error" to e.message)
        return ResponseEntity(map, HttpStatus.OK)
    }
}