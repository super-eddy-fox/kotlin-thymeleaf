package com.example.thymeleaf.model.repository

import com.example.thymeleaf.model.entity.User
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager


interface UserCustom {
    fun save(user: User):User
}

@Repository
class UserCustomImpl(private val em:EntityManager,
                     private val jpaQueryFactory: JPAQueryFactory
) : UserCustom{
    override fun save(user: User): User {
        em.persist(user)
        return user
    }
}


interface UserRepository : JpaRepository<User,Long>, QuerydslPredicateExecutor<User>,UserCustom {

    @EntityGraph(attributePaths = ["boards"])
    override fun findAll():List<User>

    fun findByUserName(name: String):User?
}