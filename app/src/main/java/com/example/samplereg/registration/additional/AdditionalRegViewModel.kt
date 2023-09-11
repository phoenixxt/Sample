package com.example.samplereg.registration.additional

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplereg.R
import com.example.samplereg.ResourcesRepository
import com.example.samplereg.registration.AdditionalInfo
import com.example.samplereg.registration.RegistrationFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class AdditionalInfoState(
    val firstAdditionalError: String? = null,
    val secondAdditionalError: String? = null,
    val isDoneAvailable: Boolean = false
)

@HiltViewModel
class AdditionalRegViewModel @Inject constructor(
    private val registrationFlow: RegistrationFlow,
    private val additionalTextValidation: AdditionalTextValidation,
    private val resourcesRepository: ResourcesRepository
) : ViewModel() {

    private val _state = MutableLiveData(AdditionalInfoState(additionalFieldError(), additionalFieldError()))
    val state: LiveData<AdditionalInfoState> = _state

    private var currentAdditionalInfo = AdditionalInfo("", "", "")

    fun onFieldChanges(
        firstAdditionalInfo: String,
        secondAdditionalInfo: String,
        thirdAdditionalInfo: String
    ) {
        currentAdditionalInfo = AdditionalInfo(
            firstAdditionalInfo,
            secondAdditionalInfo,
            thirdAdditionalInfo
        )

        val isFirstAdditionalInfoValid = additionalTextValidation.isValid(firstAdditionalInfo)
        val isSecondAdditionalInfoValid = additionalTextValidation.isValid(secondAdditionalInfo)

        _state.value = AdditionalInfoState(
            firstAdditionalError = if (isFirstAdditionalInfoValid) null else additionalFieldError(),
            secondAdditionalError = if (isSecondAdditionalInfoValid) null else additionalFieldError(),
            isDoneAvailable = isFirstAdditionalInfoValid && isSecondAdditionalInfoValid
        )
    }

    fun next() {
        registrationFlow.additionalInfoCompleted(currentAdditionalInfo)
    }

    private fun additionalFieldError(): String {
        return resourcesRepository.getString(R.string.additional_info_error)
    }

}
