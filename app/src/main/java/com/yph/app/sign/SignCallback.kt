package com.yph.app.sign

import com.alibaba.fastjson.JSONObject
import com.koushikdutta.async.http.server.AsyncHttpServerRequest
import com.koushikdutta.async.http.server.AsyncHttpServerResponse
import com.koushikdutta.async.http.server.HttpServerRequestCallback
import de.robv.android.xposed.XposedHelpers
import java.lang.Exception

class SignCallback : HttpServerRequestCallback {
    companion object {
        const val path = "/"
    }

    override fun onRequest(request: AsyncHttpServerRequest?, response: AsyncHttpServerResponse?) {
        request?.apply {
            val msg = try {
                val p1 = JSONObject.parseObject<HashMap<String, String>>(
                    body.get().toString(),
                    HashMap::class.java
                )

                val p2 = hashMapOf("pageId" to p1["pageId"], "pageName" to p1["pageName"])
                p1.remove("pageId")
                p1.remove("pageName")

                val p3 = "25443018"

                val p4: String? = null

                val p5 = false

                val clazz = XposedHelpers.findClass(
                    SignXposed.requestIdClassName,
                    SignXposed.loadPackageParam.classLoader
                )
                val p6 = XposedHelpers.callStaticMethod(clazz, SignXposed.requestIdMethodName)

                val res = XposedHelpers.callMethod(SignXposed.instance, "a", p1, p2, p3, p4, p5, p6)
                JSONObject.toJSON(res).toString()
            } catch (e: Exception) {
                "{\"code\": -1, \"msg\": \"$e\"}"
            }

            response?.apply {
                send("application/json; charset=utf-8", msg)
            }
        }
    }
}