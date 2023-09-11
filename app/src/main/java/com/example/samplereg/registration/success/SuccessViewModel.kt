package com.example.samplereg.registration.success

import androidx.lifecycle.ViewModel
import com.example.samplereg.registration.RegistrationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SuccessViewModel @Inject constructor(
    private val registrationState: RegistrationState
) : ViewModel() {

    fun registrationData(): String = registrationState.registrationData().toString()

}