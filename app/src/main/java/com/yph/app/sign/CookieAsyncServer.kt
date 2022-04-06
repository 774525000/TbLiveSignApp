package com.yph.app.sign

import android.webkit.CookieManager
import com.alibaba.fastjson.JSONObject
import com.koushikdutta.async.http.server.AsyncHttpServer

class CookieAsyncServer(private val port: Int) {
    private val server = AsyncHttpServer()
    private var isOpen = false

    private fun initRouter() {
        server.get(
            "/"
        ) { request, response ->
            request?.apply {
                val cookie = CookieManager.getInstance().getCookie("taobao.com")
                response?.apply {
                    val cookieJson = hashMapOf("code" to "success", "cookie" to (cookie ?: ""))
                    send("application/json; charset=utf-8", JSONObject.toJSONString(cookieJson))
                }
            }
        }
    }

    fun start() {
        if (isOpen) return
        isOpen = true
        initRouter()
        server.listen(port)
    }

    fun stop() {
        server.stop()
    }
}