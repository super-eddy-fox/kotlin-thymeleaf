package com.example.thymeleaf.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/board")
class BoardController {

    @GetMapping("/list")
    fun list():String{
        return "board/list"
    }
}