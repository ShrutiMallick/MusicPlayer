package com.example.musicplayer.data.remote


sealed class NetworkResponse<out T : Any> {

    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()

    data class Error(val error: String, val status: Int = 0) : NetworkResponse<Nothing>()
}
