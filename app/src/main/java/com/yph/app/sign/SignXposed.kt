package com.yph.app.sign

import android.os.Bundle
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class SignXposed : IXposedHookLoadPackage {
    companion object {
        lateinit var loadPackageParam: XC_LoadPackage.LoadPackageParam
        const val packageName = "com.taobao.live"
        const val homeActivity = "com.taobao.live.home.activity.TaoLiveHomeActivity"
        const val requestIdClassName = "mtopsdk.mtop.util.RequestIdGenerator"
        const val requestIdMethodName = "getRequestId"
        const val spdyClassName = "mtopsdk.mtop.global.SwitchConfig"
        lateinit var instance: Any
    }

    private val signAsyncServer = SignAsyncServer(10086)
    private val cookieAsyncServer = CookieAsyncServer(10010)

    override fun handleLoadPackage(lp: XC_LoadPackage.LoadPackageParam?) {
        cookieAsyncServer.start()

        if (lp == null || !lp.packageName.equals(packageName)) return
        loadPackageParam = lp
        /**
         * 启动服务
         */
        XposedHelpers.findAndHookMethod(
            homeActivity,
            lp.classLoader,
            "onCreate",
            Bundle::class.java,
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    super.afterHookedMethod(param)

                    signAsyncServer.start()
                }
            })

        /**
         * 关闭服务
         */
        XposedHelpers.findAndHookMethod(
            homeActivity,
            lp.classLoader,
            "onDestroy",
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)

                    signAsyncServer.stop()
                }
            })

        /**
         * 保存实例
         */
        XposedHelpers.findAndHookConstructor("tb.jrb", lp.classLoader, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam?) {
                super.afterHookedMethod(param)
                param?.apply {
                    instance = thisObject
                }
            }
        })


        /**
         * 禁用 spdy
         */
        XposedHelpers.findAndHookMethod(
            spdyClassName,
            lp.classLoader,
            "isGlobalSpdySwitchOpen",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    super.afterHookedMethod(param)
                    param?.result = false
                }
            })


        /**
         * 禁用 spdy
         */
        XposedHelpers.findAndHookMethod(
            spdyClassName,
            lp.classLoader,
            "isGlobalSpdySslSwitchOpen",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    super.afterHookedMethod(param)
                    param?.result = false
                }
            })
    }
}