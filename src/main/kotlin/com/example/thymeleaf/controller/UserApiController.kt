package com.example.thymeleaf.controller
import com.example.thymeleaf.model.entity.Board
import com.example.thymeleaf.model.entity.User
import com.example.thymeleaf.model.repository.BoardRepository
import com.example.thymeleaf.model.repository.UserRepository
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import org.thymeleaf.util.StringUtils

class UserNotFoundException(id: Long):RuntimeException("{ $id is not exist}")

@RestController
@RequestMapping("/api")
class UserApiController(private val repository: UserRepository) {
    @GetMapping("/users")
    fun all(@RequestParam(required = false, defaultValue = "") title:String
            ,@RequestParam(required = false, defaultValue = "") content:String): List<User> {
        return repository.findAll()
    }
    @PostMapping("/users")
    fun newUser(@RequestBody newUser: User): User {
        return repository.save(newUser)
    }
    // Single item
    @GetMapping("/users/{id}")
    fun one(@PathVariable id: Long): User? {
        return repository.findById(id)
            .orElseThrow { null }
    }
//    @PutMapping("/users/{id}")
//    fun replaceBoard(@RequestBody newBoard: Board, @PathVariable id: Long): Board {
//        return repository.findById(id)
//            .map { user ->
//                repository.save(user)
//            }
//            .orElseGet {
//                newBoard.id = id
//                repository.save(newBoard)
//            }
//    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long) {
        repository.deleteById(id)
    }
}