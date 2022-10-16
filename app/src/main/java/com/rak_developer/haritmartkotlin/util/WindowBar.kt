package com.rak_developer.haritmartkotlin.util

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import com.rak_developer.haritmartkotlin.R

class WindowBar {

    fun setColor(activity: Activity) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, R.color.green));
                activity.window.statusBarColor =
                    activity.resources.getColor(R.color.colorPrimaryDark)
                activity.window.navigationBarColor =
                    activity.resources.getColor(R.color.colorPrimaryDark)
            }
        } catch (e: Exception) {
        }
    }
}