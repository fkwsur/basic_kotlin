package com.example.demo.controller

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.*;
import com.example.demo.models.*;
// import com.example.demo.service.*;
import com.example.demo.utills.*;
import com.example.demo.repository.*;
import lombok.extern.slf4j.Slf4j;
import kotlinx.coroutines.*


@Slf4j
@Controller
class BoardController(
    private val r: Response,
    private val jwt:Jwt,
    private val boardRepository:BoardRepository
) {

    // @PostMapping("/bulk/board")
    // fun bulkBoard(
    // @RequestHeader(value="xauth") token: String,    
    // @RequestBody boards: List<Board>): ResponseEntity<Any> {

    // }
}