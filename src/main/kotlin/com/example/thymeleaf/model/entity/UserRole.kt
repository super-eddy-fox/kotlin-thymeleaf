//package com.example.thymeleaf.model.entity
//
//import java.io.Serializable
//import javax.persistence.Column
//import javax.persistence.Embeddable
//import javax.persistence.EmbeddedId
//import javax.persistence.Entity
//
//@Embeddable
//data class UserRolePk (
//    @Column(nullable = false, columnDefinition = "bigint(20) unsigned")
//    var userId:Long,
//    @Column(nullable = false, columnDefinition = "bigint(20) unsigned")
//    var roleId:Long
//) : Serializable
//
//@Entity
//class UserRole(@EmbeddedId var pk:UserRolePk)
//{
//
//}