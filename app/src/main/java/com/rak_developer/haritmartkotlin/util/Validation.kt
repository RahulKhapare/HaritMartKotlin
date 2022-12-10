package com.rak_developer.haritmartkotlin.util

import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

object Validation {

    fun validEmail(email: String?): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun capitalize(capString: String): String? {
        var returnValue = ""
        returnValue = try {
            val capBuffer = StringBuffer()
            val capMatcher =
                Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString)
            while (capMatcher.find()) {
                (capMatcher as Matcher).appendReplacement(
                    capBuffer,
                    capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase()
                )
            }
            capMatcher.appendTail(capBuffer).toString()
        } catch (e: Exception) {
            capString
        }
        return returnValue
    }
}