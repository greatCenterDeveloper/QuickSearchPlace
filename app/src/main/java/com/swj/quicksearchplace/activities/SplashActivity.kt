package com.swj.quicksearchplace.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash) // 테마를 이용하여 화면을 구현할 것임

        // 단순하게 1.5초 후에 로그인 화면(LoginActivity)로 전환
//        Handler(Looper.getMainLooper()).postDelayed( object:Runnable{
//            override fun run() {
//
//            }
//        },1500)

        // lambda 표기로 축약
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },1500)
    }
}