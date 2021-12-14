package com.example.thymeleaf.controller
import com.example.thymeleaf.model.entity.Board
import com.example.thymeleaf.model.repository.BoardRepository
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import org.thymeleaf.util.StringUtils

class BoardNotFoundException(id: Long):RuntimeException("{ $id is not exist}")

@RestController
@RequestMapping("/api")
class BoardApiController(private val repository: BoardRepository) {
    @GetMapping("/boards")
    fun all(@RequestParam(required = false, defaultValue = "") title:String
            ,@RequestParam(required = false, defaultValue = "") content:String): List<Board> {
        return if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)){
            repository.findAll()
        }else{
            repository.findByTitleOrContent(title,content)
        }

    }
    @PostMapping("/boards")
    fun newBoard(@RequestBody newBoard: Board): Board {
        return repository.save(newBoard)
    }
    // Single item
    @GetMapping("/boards/{id}")
    fun one(@PathVariable id: Long): Board? {
        return repository.findById(id)
            .orElseThrow { null }
    }
    @PutMapping("/boards/{id}")
    fun replaceBoard(@RequestBody newBoard: Board, @PathVariable id: Long): Board {
        return repository.findById(id)
            .map { Board ->
                Board.title = newBoard.title
                Board.content = newBoard.content
                repository.save(Board)
            }
            .orElseGet {
                newBoard.id = id
                repository.save(newBoard)
            }
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/boards/{id}")
    fun deleteBoard(@PathVariable id: Long) {
        repository.deleteById(id)
    }
}