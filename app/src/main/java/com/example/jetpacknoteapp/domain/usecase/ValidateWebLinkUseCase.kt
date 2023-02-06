package com.example.jetpacknoteapp.domain.usecase

import android.util.Patterns
import com.example.jetpacknoteapp.domain.model.ValidateResult
import javax.inject.Inject

class ValidateWebLinkUseCase @Inject constructor() {
    operator fun invoke(webLink: String): ValidateResult {
        if (webLink.isBlank())
            return ValidateResult(error = "Please enter url")
        if (!Patterns.WEB_URL.matcher(webLink).matches())
            return ValidateResult(error = "Url is not valid")
        return ValidateResult()
    }
}