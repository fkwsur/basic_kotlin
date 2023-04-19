package com.example.demo.controller

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus

import java.util.*
import com.example.demo.models.*
import com.example.demo.utills.*
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
class TestController(

    private val bcrypt:Bcrypt, 
    private val jwt:Jwt, 

){
    @GetMapping("/hi")
    fun Start(): ResponseEntity<HashMap<String, String>> {
        var map = HashMap<String, String>()
        map.put("result", "hi")
        return ResponseEntity(map, HttpStatus.OK)
    }

    @GetMapping("/name")
    fun GetQ(@RequestParam("name") name:String ): ResponseEntity<HashMap<String, String>> {
        var map = HashMap<String, String>()
        map.put("result", name)
        return ResponseEntity(map, HttpStatus.OK)
    }

    @GetMapping("/body")
    fun GetBody(@RequestBody req: SimpleVo ): ResponseEntity<SimpleVo> {
        return ResponseEntity(req, HttpStatus.OK)
    }

    @PostMapping("/bind")
    fun bind(@RequestBody req:UserVo):ResponseEntity<PreUser> {
        return ResponseEntity(req.bind(), HttpStatus.OK);
    }

    @PostMapping("/bind2")
    fun bind2(@RequestBody req:UserVo):ResponseEntity<PreUser> {
        req.password = bcrypt.hashPassword(req.password);
        return ResponseEntity(req.bind(), HttpStatus.OK);
    }

    @PostMapping("issuetoken")
    fun issuetoken(@RequestBody req:UserVo):ResponseEntity<ViewToken> {
        var token = ViewToken(jwt.CreateToken(req.id, req.password))
        return ResponseEntity(token, HttpStatus.OK);
    }

}
