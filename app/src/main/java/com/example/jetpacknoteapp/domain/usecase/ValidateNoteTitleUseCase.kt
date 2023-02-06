package com.example.jetpacknoteapp.domain.usecase

import com.example.jetpacknoteapp.domain.model.ValidateResult
import javax.inject.Inject

class ValidateNoteTitleUseCase @Inject constructor(){
    operator fun invoke(title:String): ValidateResult {
        if (title.isBlank())
            return ValidateResult(error = "Please enter title")
        if (title.length<2)
            return ValidateResult(error = "Title is so short")
        return ValidateResult()
    }
}