package com.example.samplereg.registration

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

data class Name(
    val firstName: String,
    val lastName: String
)

data class AdditionalInfo(
    val firstInfo: String,
    val secondInfo: String,
    val thirdInfo: String
)

data class RegistrationData(
    val name: Name,
    val email: String,
    val additionalInfo: AdditionalInfo
)

@ActivityRetainedScoped
class RegistrationState @Inject constructor() {

    private var name: Name? = null

    private var email: String? = null

    private var additionalInfo: AdditionalInfo? = null

    fun provideName(name: Name) {
        this.name = name
    }

    fun provideEmail(email: String) {
        this.email = email
    }

    fun provideAdditionalData(additionalInfo: AdditionalInfo) {
        this.additionalInfo = additionalInfo
    }

    fun registrationData(): RegistrationData? {
        val name = name ?: return null
        val email = email ?: return null
        val additionalInfo = additionalInfo ?: return null
        return RegistrationData(
            name,
            email,
            additionalInfo
        )
    }
}
