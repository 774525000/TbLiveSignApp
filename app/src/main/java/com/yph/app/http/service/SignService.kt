package com.yph.app.http.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface SignService {
    @GET
    fun getSign(@Url p2: String): Call<ResponseBody>
}