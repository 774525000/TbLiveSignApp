package com.yph.app.http

import com.yph.app.App
import com.yph.app.http.service.SignService
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy

object Request {
    private val retrofit =
        Retrofit.Builder().client(object : OkHttpClient() {
            override fun proxy(): Proxy? {
                /*return Proxy.NO_PROXY*/
                return super.proxy()
            }
        }).baseUrl(App.baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()
    private val signService = retrofit.create(SignService::class.java)

    fun getSign(callback: Callback<ResponseBody>) {
        val params = hashMapOf("data" to "{}")
        signService.getSign("http://www.baidu.com").enqueue(callback)
    }
}