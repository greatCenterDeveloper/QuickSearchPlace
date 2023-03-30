package com.swj.quicksearchplace.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.swj.quicksearchplace.G
import com.swj.quicksearchplace.R
import com.swj.quicksearchplace.databinding.ActivityEmailSigninBinding
import com.swj.quicksearchplace.model.UserAccount

class EmailSigninActivity : AppCompatActivity() {

    lateinit var binding:ActivityEmailSigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailSigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바를 액션바로 설정
        setSupportActionBar(binding.toolbar)
        // 툴바에 업버튼 보이기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_arrow_back)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.btnSignin.setOnClickListener { clickSignIn() }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun clickSignIn() {
        var email:String = binding.etEmail.text.toString()
        var password:String = binding.etPassword.text.toString()

        // Firebase Firestore DB에서 이메일과 패스워드 확인
        val db:FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("emailUsers")
            .whereEqualTo("email", email)
            .whereEqualTo("password", password)
            .get().addOnSuccessListener {
                if(it.documents.size > 0) {
                    // 로그인 성공
                    var id:String = it.documents[0].id // document 명
                    G.userAccount = UserAccount(id, email)

                    // 로그인 성공했으니.. 곧바로 MainActivity로 이동
                    val intent = Intent(this, MainActivity::class.java)

                    // 기존 task의 모든 액티비티들을 제거하고 새로운 task를 시작
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                    startActivity(intent)
                } else {
                    // 로그인 실패 이므로..
                    AlertDialog.Builder(this)
                        .setMessage("이메일과 비밀번호가 일치하지 않습니다.")
                        .setPositiveButton("OK", null).show()
                    binding.etEmail.requestFocus()
                    binding.etEmail.selectAll()
                }
            }
    }
}