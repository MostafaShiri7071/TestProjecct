package com.my.testproject.core

import android.content.Context

/**
 * Created by Mostafa Shiri.
 */
object Utils {

    fun myyy(context: Context): String? {
        return context.sharedPreferencesHelper().getString(
            Constant.TOKEN)
    }
}