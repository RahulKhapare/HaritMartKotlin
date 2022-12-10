package com.rak_developer.haritmartkotlin.util

import android.content.Context
import android.view.Gravity
import android.widget.Toast

object Toast {

    public fun showMessage(context: Context?, msg: String?) {
        val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 200)
        toast.show()
    }
}