package com.yph.app.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.yph.app.databinding.ActivityMainBinding
import com.yph.app.http.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URI

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
        val path = binding.pathEdit.text.toString()
        if (path.isBlank()) {
            Toast.makeText(this, "请输入正确的路径", Toast.LENGTH_SHORT).show()
            return
        }
        Request.getSign(path.trim(), object : Callback<ResponseBody> {
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