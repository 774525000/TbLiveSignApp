package com.yph.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.yph.app.databinding.ActivityMainBinding
import com.yph.app.http.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindEvent()
    }

    private fun bindEvent() {
        binding.send.setOnClickListener {
            getSign()
        }
    }


    private fun getSign() {
        Request.getSign(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.body()?.apply {
                    binding.resEdit.text = Editable.Factory.getInstance().newEditable(string())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println(t.message)
            }

        })
    }
}