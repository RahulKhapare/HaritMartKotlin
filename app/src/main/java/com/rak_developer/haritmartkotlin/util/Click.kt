package com.rak_developer.haritmartkotlin.util

import android.view.View

object Click {
    fun preventTwoClick(view: View) {
        view.isEnabled = false
        view.postDelayed({ view.isEnabled = true }, 1000)
    }
}
