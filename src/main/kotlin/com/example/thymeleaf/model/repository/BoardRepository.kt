package com.example.thymeleaf.model.repository

import com.example.thymeleaf.model.entity.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface BoardRepository : JpaRepository<Board,Long>, QuerydslPredicateExecutor<Board> {
    fun findByTitle(title: String): List<Board>
    fun findByTitleOrContent(title: String, content: String): List<Board>
    fun findByTitleContainingOrContentContaining(searchText: String, searchText1: String, pageable: Pageable): Page<Board>
}