package com.swj.quicksearchplace.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.swj.quicksearchplace.R
import com.swj.quicksearchplace.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    val binding:ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 둘러보기 버튼 클릭으로 로그인없이 Main 화면으로 이동
        binding.tvGo.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // 회원가입 버튼 클릭
        binding.tvSignup.setOnClickListener {
            // 회원가입 화면으로 전환
            startActivity(Intent(this, SignupActivity::class.java))
        }

        // 이메일 로그인 버튼 클릭
        binding.layoutEmail.setOnClickListener {
            // 이메일 로그인 화면 전환
            startActivity(Intent(this, EmailSigninActivity::class.java))
        }

        // 간편 로그인 버튼들 클릭
        binding.ivLoginKakao.setOnClickListener { clickLoginKakao() }
        binding.ivLoginGoogle.setOnClickListener { clickLoginGoogle() }
        binding.ivLoginNaver.setOnClickListener { clickLoginNaver() }
    }

    private fun clickLoginKakao() {

    }

    private fun clickLoginGoogle() {

    }

    private fun clickLoginNaver() {

    }
}