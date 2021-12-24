package com.yph.app.http.service

import com.yph.app.model.Sign
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface SignService {
    @POST("/")
    fun getSign(@Body p1: HashMap<String, String>): Call<Sign>
}