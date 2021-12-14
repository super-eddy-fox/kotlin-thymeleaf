package com.example.thymeleaf.controller

import com.example.thymeleaf.model.entity.User
import com.example.thymeleaf.model.repository.UserRepository
import com.example.thymeleaf.service.UserService
import org.mariadb.jdbc.internal.com.send.parameters.StringParameter
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/account")
class AccountController(private val userService: UserService) {

    @GetMapping("/login")
    fun login():String{
        return "account/login"
    }

    @GetMapping("/register")
    fun register():String{
        return "account/register"
    }

    @PostMapping("/register")
    fun register(user: User):String{
        userService.save(user)
        return "redirect:/"
    }

}