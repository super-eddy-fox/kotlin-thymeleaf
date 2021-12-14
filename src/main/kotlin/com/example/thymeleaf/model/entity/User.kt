package com.example.thymeleaf.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(name = "unique_user_userName", columnNames =["userName"])])
class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint(20) unsigned")
    var id:Long? = null
    @Column(nullable = false,columnDefinition = "varchar(50)")
    var userName:String = ""
    @Column(nullable = false,columnDefinition = "varchar(100)")
    var password:String = ""
    @Column(nullable = false)
    var enabled:Boolean = false


    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")])
    open var roles: MutableList<Role> = mutableListOf()

//    @OneToMany(mappedBy = "user" , cascade = [CascadeType.ALL] , orphanRemoval = true)
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    val boards:List<Board> = mutableListOf()

}