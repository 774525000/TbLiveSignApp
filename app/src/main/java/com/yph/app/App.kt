package com.yph.app

import android.app.Application

class App : Application() {
    companion object {
        const val baseUrl = "http://127.0.0.1:10086"
    }
}