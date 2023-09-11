package com.example.samplereg.registration.additional

import com.example.samplereg.registration.validation.TextLengthValidation
import javax.inject.Inject

class AdditionalTextValidation @Inject constructor(private val textLengthValidation: TextLengthValidation) {

    fun isValid(additionalText: String): Boolean {
        return textLengthValidation.isValid(additionalText, MIN_LENGTH)
    }

    private companion object {
        const val MIN_LENGTH = 20
    }
}