package com.example.samplereg

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourcesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getString(@StringRes stringId: Int): String {
        return context.resources.getString(stringId)
    }

}
