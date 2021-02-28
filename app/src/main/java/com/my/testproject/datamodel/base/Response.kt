package com.my.testproject.datamodel.base

import com.s.testproject.datamodel.base.Status

/**
 * Created by Mostafa Shiri.
 */
data class Response<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Response<T> {
            return Response(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T?): Response<T> {
            return Response(
                Status.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T?): Response<T> {
            return Response(
                Status.LOADING,
                data,
                null
            )
        }

    }
}