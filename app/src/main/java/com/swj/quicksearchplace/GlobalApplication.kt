package com.swj.quicksearchplace

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Kakao SDK 초기화 - 개발자 사이트에 등록한 [네이티브 앱키]
        KakaoSdk.init(this, "8c0347aefb9bf9b817a363a979ad7382")
    }
}