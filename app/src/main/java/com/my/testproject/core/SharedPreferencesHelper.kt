package com.my.testproject.core

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class SharedPreferencesHelper() {

    private lateinit var sharedpreferences: SharedPreferences

    constructor(context: Context, name: String) : this() {
        sharedpreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    //Setter

    fun setString(key: String, value: String) {
        val editor = sharedpreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun setInt(key: String, value: Int) {
        val editor = sharedpreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun setFloat(key: String, value: Float) {
        val editor = sharedpreferences.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun setLong(key: String, value: Long) {
        val editor = sharedpreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun setObject(key: String, value: Any) {
        val editor = sharedpreferences.edit()
        val json = Gson().toJson(value)
        editor.putString(key, json)
        editor.apply()
    }

    //Getter

    fun getString(key: String): String {
        return if (sharedpreferences.getString(key,"").isNullOrEmpty())
            ""
        else
            sharedpreferences.getString(key,"")!!
    }

    fun getInt(key: String): Int {
        return sharedpreferences.getInt(key, 0)
    }

    fun getFloat(key: String): Float {
        return sharedpreferences.getFloat(key, 0f)
    }

    fun getLong(key: String): Long {
        return sharedpreferences.getLong(key, 0L)
    }

    fun getObject(key: String, javaClass: Class<*>): Any {
        val json = sharedpreferences.getString(key, "")
        return Gson().fromJson(json, javaClass)
    }


    fun clearAll(context: Context) {
        val editor = sharedpreferences.edit()
        editor.clear()
        editor.apply()
    }

    enum class Name(val key: String) {
        User("User"),
    }

}

