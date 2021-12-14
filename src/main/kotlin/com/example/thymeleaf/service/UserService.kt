package com.example.thymeleaf.service

import com.example.thymeleaf.model.entity.Role
import com.example.thymeleaf.model.entity.User
import com.example.thymeleaf.model.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
    ) {

    fun save(user: User) {
        user.password = passwordEncoder.encode(user.password)
        user.enabled = true
        val role = Role()
        role.id = 1
        user.roles.add(role)
        userRepository.save(user)
    }

}