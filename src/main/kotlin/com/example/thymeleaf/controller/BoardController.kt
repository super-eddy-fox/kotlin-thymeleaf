package com.example.thymeleaf.controller

import com.example.thymeleaf.model.entity.Board
import com.example.thymeleaf.model.repository.BoardRepository
import com.example.thymeleaf.model.validator.BoardValidator
import com.example.thymeleaf.service.BoardService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@Controller
@RequestMapping("/board")
class BoardController(
    private val boardService: BoardService,
    private val boardRepository:BoardRepository,
    private val boardValidator: BoardValidator) {

    @GetMapping("/list")
    fun list(model: Model,@PageableDefault(size = 4) pageable: Pageable,@RequestParam(required = false, defaultValue = "") searchText:String):String{

        val boards = boardRepository.findByTitleContainingOrContentContaining(searchText,searchText,pageable)

        val startPage = Math.max(1,boards.pageable.pageNumber - 4)
        val endPage = Math.min(boards.totalPages,boards.pageable.pageNumber + 4)
        model.addAttribute("startPage",startPage)
        model.addAttribute("endPage",endPage)
        model.addAttribute("boards",boards)
        return "board/list"
    }

    @GetMapping("/form")
    fun form(model: Model,@RequestParam(required = false)id:Long?):String {
        if(id == null){
            model.addAttribute("board", Board())
        }else{
            val board = boardRepository.findById(id).orElse(null)
            model.addAttribute("board",board)
        }
        return "board/form"
    }

    @PostMapping("/form")
    fun greetingSubmit(@Valid board: Board, bindingResult: BindingResult,authentication:Authentication): String? {

        if(bindingResult.hasErrors()){
            return "board/form"
        }

        boardService.save(authentication.name,board)
        return "redirect:/board/list"
    }
}