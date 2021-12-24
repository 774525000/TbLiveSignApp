package com.yph.app.sign

import com.koushikdutta.async.http.server.AsyncHttpServer
import com.yph.app.sign.SignCallback

class SignAsyncServer(private val port: Int) {
    private val server = AsyncHttpServer()

    private fun initRouter() {
        server.post(SignCallback.path, SignCallback())
    }

    fun start() {
        initRouter()
        server.listen(port)
    }

    fun stop() {
        server.stop()
    }
}