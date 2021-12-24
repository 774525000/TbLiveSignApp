package com.yph.app.http

import com.yph.app.App
import com.yph.app.http.service.SignService
import com.yph.app.model.Sign
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Request {
    private val retrofit =
        Retrofit.Builder().baseUrl(App.baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()
    private val signService = retrofit.create(SignService::class.java)

    fun getSign(callback: Callback<Sign>) {
        val a = hashMapOf("data" to "{}")
        signService.getSign(a).enqueue(callback)
    }
}