package com.example.samplereg.registration.validation

import javax.inject.Inject

class TextLengthValidation @Inject constructor() {

    fun isValid(text: String, requiredLength: Int) = text.length >= requiredLength

}
