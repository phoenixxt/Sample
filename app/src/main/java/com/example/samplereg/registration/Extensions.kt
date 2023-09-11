package com.example.samplereg.registration

import android.widget.TextView
import androidx.core.widget.doOnTextChanged

fun onMultipleTextFieldsChanges(vararg textViews: TextView, onChangeHappened: () -> Unit) {
    textViews.forEachIndexed { index, textView ->
        textView.doOnTextChanged { _, _, _, _ ->
            onChangeHappened()
        }
    }
}

val TextView.stringText
    get() = text.toString()
