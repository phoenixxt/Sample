package com.example.samplereg.registration.name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplereg.R
import com.example.samplereg.ResourcesRepository
import com.example.samplereg.registration.Name
import com.example.samplereg.registration.RegistrationFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class NameRegState(
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val isNextAvailable: Boolean = false
)

@HiltViewModel
class NameRegViewModel @Inject constructor(
    private val registrationFlow: RegistrationFlow,
    private val nameValidation: NameValidation,
    private val resourcesRepository: ResourcesRepository
) : ViewModel() {

    private val _state = MutableLiveData(NameRegState(firstNameError(), lastNameError()))
    val state: LiveData<NameRegState> = _state

    private var currentName = Name("", "")

    fun onFieldChanges(firstName: String, lastName: String) {
        currentName = Name(firstName, lastName)

        val isFirstNameValid = nameValidation.isValid(firstName)
        val isLastNameValid = nameValidation.isValid(lastName)

        _state.value = NameRegState(
            firstNameError = if (isFirstNameValid) null else firstNameError(),
            lastNameError = if (isLastNameValid) null else lastNameError(),
            isNextAvailable = isFirstNameValid && isLastNameValid
        )
    }

    fun next() {
        registrationFlow.nameCompleted(currentName)
    }

    private fun firstNameError(): String {
        return resourcesRepository.getString(R.string.first_name_error)
    }

    private fun lastNameError(): String {
        return resourcesRepository.getString(R.string.last_name_error)
    }

}
