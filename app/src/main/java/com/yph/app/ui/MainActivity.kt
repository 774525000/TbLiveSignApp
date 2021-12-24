package com.yph.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.yph.app.databinding.ActivityMainBinding
import com.yph.app.http.Request
import com.yph.app.model.Sign
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
        Request.getSign(object : Callback<Sign> {
            override fun onResponse(call: Call<Sign>, response: Response<Sign>) {
                response.body()?.apply {
                    binding.resEdit.text = Editable.Factory.getInstance().newEditable(xSign)
                }
            }

            override fun onFailure(call: Call<Sign>, t: Throwable) {
                println(t.message)
            }

        })
    }
}