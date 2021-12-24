package com.yph.app

import com.koushikdutta.async.http.server.AsyncHttpServer

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