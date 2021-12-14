package com.example.thymeleaf.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class Board{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id:Long? = null
    @NotNull
    @Size(min=2, max=30, message = "제목은 2자이상 30자 이하입니다. ")
    var title:String = ""
    var content:String = ""

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    var user:User? = null

}