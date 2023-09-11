package com.example.samplereg

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.samplereg.registration.additional.AdditionalRegFragment
import com.example.samplereg.registration.email.EmailRegFragment
import com.example.samplereg.registration.name.NameRegFragment
import com.example.samplereg.registration.success.SuccessFragment
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Navigation @Inject constructor() {

    private var currentActivity: FragmentActivity? = null

    fun provideActivity(activity: FragmentActivity) {
        currentActivity = activity
    }

    fun activityDestroyed() {
        currentActivity = null
    }

    fun openStartingScreen() {
        fragmentNavigate {
            replace(R.id.flRoot, NameRegFragment())
        }
    }

    fun openEmailRegScreen() {
        fragmentNavigate {
            replace(R.id.flRoot, EmailRegFragment())
                .addToBackStack(null)
        }
    }

    fun openAdditionalRegScreen() {
        fragmentNavigate {
            replace(R.id.flRoot, AdditionalRegFragment())
                .addToBackStack(null)
        }
    }

    fun openSuccessPage() {
        currentActivity?.supportFragmentManager?.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        fragmentNavigate {
            replace(R.id.flRoot, SuccessFragment())
        }
    }

    private fun fragmentNavigate(transaction: FragmentTransaction.() -> FragmentTransaction) {
        currentActivity?.supportFragmentManager
            ?.beginTransaction()
            ?.transaction()
            ?.commit()
    }

}