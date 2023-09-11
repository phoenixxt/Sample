package com.example.samplereg.registration

import com.example.samplereg.Navigation
import javax.inject.Inject

class RegistrationFlow @Inject constructor(
    private val registrationState: RegistrationState,
    private val navigation: Navigation
) {

    fun nameCompleted(name: Name) {
        registrationState.provideName(name)
        navigation.openEmailRegScreen()
    }

    fun emailCompleted(email: String) {
        registrationState.provideEmail(email)
        navigation.openAdditionalRegScreen()
    }

    fun additionalInfoCompleted(additionalInfo: AdditionalInfo) {
        registrationState.provideAdditionalData(additionalInfo)
        navigation.openSuccessPage()
    }

}
