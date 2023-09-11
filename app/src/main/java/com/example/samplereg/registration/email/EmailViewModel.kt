package com.example.samplereg.registration.email

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplereg.R
import com.example.samplereg.ResourcesRepository
import com.example.samplereg.registration.RegistrationFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class EmailState(
    val emailError: String? = null,
    val repeatEmailError: String? = null,
    val isNextAvailable: Boolean = false
)

@HiltViewModel
class EmailViewModel @Inject constructor(
    private val registrationFlow: RegistrationFlow,
    private val emailValidation: EmailValidation,
    private val resourcesRepository: ResourcesRepository
) : ViewModel() {

    private val _state = MutableLiveData(EmailState(emailError(), repeatEmailError()))
    val state: LiveData<EmailState> = _state

    private var currentEmail = ""

    fun onFieldChanges(email: String, repeatEmail: String) {
        currentEmail = email

        val isEmailValid = emailValidation.isValid(email)
        val isRepeatEmailValid = emailValidation.isRepetitionValid(email, repeatEmail)

        _state.value = EmailState(
            emailError = if (isEmailValid) null else emailError(),
            repeatEmailError = if (isRepeatEmailValid) null else repeatEmailError(),
            isNextAvailable = isEmailValid && isRepeatEmailValid
        )
    }

    fun next() {
        registrationFlow.emailCompleted(currentEmail)
    }

    private fun emailError(): String {
        return resourcesRepository.getString(R.string.email_error)
    }

    private fun repeatEmailError(): String {
        return resourcesRepository.getString(R.string.repeat_email_error)
    }

}
