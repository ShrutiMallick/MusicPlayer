package com.example.musicplayer.data.remote.retrofit.interceptor

import android.content.Context
import com.example.musicplayer.data.remote.retrofit.exception.NetworkException
import com.github.dhaval2404.itunesplayer.data.remote.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Intercept the request and check if Network is connected, If not throw exceptions
 *
 *
 */
class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!NetworkUtil.isConnected(context)) {
            throw NetworkException()
        }
        return chain.proceed(request)
    }
}
