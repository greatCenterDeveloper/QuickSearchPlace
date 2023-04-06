package com.swj.quicksearchplace.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.swj.quicksearchplace.R
import com.swj.quicksearchplace.databinding.ActivityPlaceUrlBinding

class PlaceUrlActivity : AppCompatActivity() {
    val binding : ActivityPlaceUrlBinding by lazy { ActivityPlaceUrlBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 현재 웹뷰 안에서 웹문서가 열리도록 설정
        // 이 설정을 안하면 자동으로 Chrome 브라우저에서 열리게 된다.
        binding.wv.webViewClient = WebViewClient()

        // 웹 문서 안에서 다이얼로그 같은 것을 발동하도록 설정
        binding.wv.webChromeClient = WebChromeClient()

        // 웹뷰는 기본적으로 보안 문제로 자바스크립트 동작을 막아놓았기에.. 이를 허용하는 코드
        // 이걸 허용해야 웹문서안에서 버튼 같은 것을 눌렀을 때 동작한다.
        binding.wv.settings.javaScriptEnabled = true

        val place_url:String = intent.getStringExtra("place_url") ?: ""

        // null은 못준다.. "" 이면 안보여준다. 에러 발생하지 않음.
        binding.wv.loadUrl(place_url)
    }

    override fun onBackPressed() {
        if(binding.wv.canGoBack()) binding.wv.goBack()
        else super.onBackPressed()
    }
}