package com.example.thymeleaf.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",columnDefinition = "bigint(20) unsigned")
    var id:Long? = null
    @Column(nullable = false, columnDefinition = "varchar(50)")
    var name:String = ""


    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    var users = mutableListOf<User>()


}