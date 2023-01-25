package com.rak_developer.haritmartkotlin.util

import android.view.View

object Click {
    //double click prevent
    fun preventTwoClick(view: View) {
        view.isEnabled = false
        view.postDelayed({ view.isEnabled = true }, 1000)
    }
}
