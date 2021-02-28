package com.my.testproject.core

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide


fun Context.sharedPreferencesHelper(name: String = "User"): SharedPreferencesHelper {
    return SharedPreferencesHelper(this, name)
}

fun Context?.toast(text: String?) {
    if (this != null) Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun ImageView.load(uri: Uri?) {
    if (uri != null) Glide.with(this).load(uri).into(this)
}

fun ImageView.load(url: String?) {
    if (url != null) Glide.with(this).load(url).into(this)
}
