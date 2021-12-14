package com.example.thymeleaf.service

import com.example.thymeleaf.model.entity.Board
import com.example.thymeleaf.model.repository.BoardRepository
import com.example.thymeleaf.model.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class BoardService(private val boardRepository: BoardRepository,
                   private val userRepository: UserRepository
) {

    fun save(name: String, board: Board) {
        val user = userRepository.findByUserName(name)
        board.user = user
        boardRepository.save(board)
    }

}