package com.example.samplereg.registration.name

import com.example.samplereg.registration.validation.TextLengthValidation
import javax.inject.Inject

class NameValidation @Inject constructor(private val textLengthValidation: TextLengthValidation) {

    fun isValid(name: String): Boolean {
        return textLengthValidation.isValid(name, NAME_MIN_LENGTH)
                && name.chars().allMatch(Character::isLetter)
    }

    private companion object {
        const val NAME_MIN_LENGTH = 2
    }

}
