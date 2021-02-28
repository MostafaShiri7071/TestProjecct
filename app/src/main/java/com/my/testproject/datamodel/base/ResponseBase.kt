package com.my.testproject.datamodel.base

import com.google.gson.annotations.SerializedName

/**
 * Created by Mostafa Shiri.
 */

open class ResponseBase<T> {

    @SerializedName("status")
    var status: String? = null

    @SerializedName("code")
    var code: String? = null

    @SerializedName("data")
    var data: T? = null
}