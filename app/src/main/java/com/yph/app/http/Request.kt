package com.yph.app.http

import com.yph.app.App
import com.yph.app.http.service.SignService
import com.yph.app.model.Sign
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy

object Request {
    private val retrofit =
        Retrofit.Builder().client(object : OkHttpClient() {
            override fun proxy(): Proxy? {
                return Proxy.NO_PROXY
            }
        }).baseUrl(App.baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()
    private val signService = retrofit.create(SignService::class.java)

    fun getSign(callback: Callback<Sign>) {
        val params = hashMapOf("data" to "{}")
        signService.getSign(params).enqueue(callback)
    }
}