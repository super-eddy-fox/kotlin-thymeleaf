package com.example.thymeleaf.model.validator

import com.example.thymeleaf.model.entity.Board
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import org.thymeleaf.util.StringUtils

@Component
class BoardValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return Board::class.java.equals(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        var board = target as Board
        if(StringUtils.isEmpty(board.content)){
            errors.rejectValue("content","key","내용을 입력하세요")
        }
    }
}