package com.example.samplereg.registration.email

import java.util.regex.Pattern
import javax.inject.Inject

class EmailValidation @Inject constructor() {

    //the best way to validate email is to send a message to that email
    private val pattern = Pattern.compile("^.*@.*\\..+\$")

    fun isValid(email: String): Boolean {
        return pattern.matcher(email).matches()
    }

    fun isRepetitionValid(email: String, repeatedEmail: String): Boolean {
        return email == repeatedEmail
    }

}
