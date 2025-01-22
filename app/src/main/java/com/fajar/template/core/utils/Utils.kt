package com.fajar.template.core.utils

import android.util.Patterns

object Utils {

    fun CharSequence.validEmailChecker() =
        Patterns
            .EMAIL_ADDRESS.matcher(this)
            .matches()
}