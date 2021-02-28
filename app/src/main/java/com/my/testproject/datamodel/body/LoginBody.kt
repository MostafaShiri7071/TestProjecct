package com.my.testproject.datamodel.body

/**
 * Created by Mostafa Shiri.
 */
data class LoginBody(
    var username: String? = null,
    var password: String? = null,
    private var titleid: String? = "3766a"
)