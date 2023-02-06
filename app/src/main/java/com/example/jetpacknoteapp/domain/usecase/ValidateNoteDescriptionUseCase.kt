package com.example.jetpacknoteapp.domain.usecase

import com.example.jetpacknoteapp.domain.model.ValidateResult
import javax.inject.Inject

class ValidateNoteDescriptionUseCase @Inject constructor(){
    operator fun invoke(description:String): ValidateResult {
        if (description.isBlank())
            return ValidateResult(error = "Please enter description")
        if (description.trim().length<3)
            return ValidateResult(error = "Description is so short")
        return ValidateResult()
    }
}