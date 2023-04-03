package com.swj.quicksearchplace.activities

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.swj.quicksearchplace.R
import com.swj.quicksearchplace.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바를 액션바로 설정
        setSupportActionBar(binding.toolbar)
        // 액션바에 업버튼 만들기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_arrow_back)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.btnSignup.setOnClickListener { clickSignUp() }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun clickSignUp() {
        // Firebase Firestore DB에 사용자 정보 저장하기
        var email:String = binding.etEmail.text.toString()
        var password:String = binding.etPassword.text.toString()
        var passwordConfirm:String = binding.etPasswordConfirm.text.toString()

        // 유효성 검사 - 패스워드와 패스워드 확인이 일치하는지 검사
        if(password != passwordConfirm) { // == 자동 equals()
            AlertDialog.Builder(this)
                .setMessage("패스워드 확인에 문제가 있습니다. 다시 확인해주시기 바랍니다.").setPositiveButton("OK", null).show()
            binding.etPasswordConfirm.selectAll()
            return
        }

        // Firestore DB instance 얻어오기
        val db = FirebaseFirestore.getInstance()

        // 저장할 값(이메일, 비밀번호)을 HashMap으로 저장
        val user:MutableMap<String, String> = mutableMapOf()
        user.put("email", email)
        user["password"] = password

        // 혹시 중복된 email을 가진 회원정보가 있을 수 있으니 확인..
        db.collection("emailUsers")
            .whereEqualTo("email", email)
            .get().addOnSuccessListener {
                // 같은 값을 가진 Document가 있다면.. 사이즈가 0개 이상일 것이므로
                if(it.documents.size > 0) {
                    AlertDialog.Builder(this)
                        .setMessage("중복된 이메일이 있습니다. 다시 확인하시기 바랍니다.")
                        .setPositiveButton("OK", null).show()
                    binding.etEmail.requestFocus()
                    binding.etEmail.selectAll()
                } else {
                    // Collection 명은 "emailUsers"로 지정 [ RDBMS 의 테이블명 같은 역할 ]
                    // 랜덤하게 만들어지는 document 명을 회원 id값으로 사용할 예정
                    //db.collection("emailUsers").document().set(user)
                    // 내부적으로 document() 이 되어 document 명이 랜덤으로 생성된다.
                    db.collection("emailUsers").add(user).addOnSuccessListener {
                        AlertDialog.Builder(this)
                            .setMessage("축하합니다.\n회원가입이 완료되었습니다.")
                            .setPositiveButton("OK", object :OnClickListener{
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    // 회원가입이 완료되었으므로 더이상 회원가입 화면을 보고있을 이유가 없다.
                                    finish()
                                }
                            }).create().show()
                    }
                }
            }
    }
}