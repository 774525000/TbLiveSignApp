package com.yph.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yph.app.databinding.ActivityMainBinding

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

    }
}