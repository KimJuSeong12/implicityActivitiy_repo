package com.example.implicityactivitiy

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.example.implicityactivitiy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnWeb.setOnClickListener(this)
        binding.btnCamera.setOnClickListener(this)
        binding.btnGoogle.setOnClickListener(this)
        binding.btnSearch.setOnClickListener(this)
        binding.btnText.setOnClickListener(this)
        binding.btnImplicity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        try {
            when (v?.id) {
                //웹뷰를 암시적 인텐트로 불러옴
                R.id.btnWeb -> {
                    val intent = Intent()
                    //주소를 불러오는 방법
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("http://www.naver.com")
                    startActivity(intent)
                }
                R.id.btnGoogle -> {
                    val latitude = 38.111111 // 경도
                    val logitude = 127.111111 // 위도
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse("http://www.google.com/maps?q=${latitude},${logitude}")
                    startActivity(intent)
                }
                R.id.btnSearch -> {
                    val intent = Intent()
                    // ACTION_WEB_SEARCH: 버튼 클릭 시 특정 키워드를 검색창에 입력시키는 기능
                    intent.action = Intent.ACTION_WEB_SEARCH
                    intent.putExtra(SearchManager.QUERY, binding.edtSearch.text.toString())
                    startActivity(intent)
                }
                R.id.btnText -> {
                    val intent = Intent()
                    intent.action = Intent.ACTION_SENDTO // ACTION_SENDTO : SMS 보내는 방법
                    intent.putExtra("sms_body", "${binding.edtSearch.text.toString()}")
                    intent.data = Uri.parse("smsto: " + Uri.encode("010-2971-4007"))
                    startActivity(intent)
                }
                //카메라를 암시적 인텐트로 불러옴
                R.id.btnCamera -> {
                    val intent = Intent()
                    //카메라 불러오는 방법
                    intent.action = MediaStore.ACTION_IMAGE_CAPTURE
                    startActivity(intent)
                }
                R.id.btnImplicity -> {
                    val intent = Intent()
                    intent.action = "ACTION_EDIT" // 매니페스트에서 커스텀으로 만든것
                    intent.putExtra("message", "${binding.edtSearch.text.toString()}")
                    startActivity(intent)
                }
            }
        } catch (e: java.lang.Exception) {
            Log.e("MainActivity", "${e.stackTrace.toString()}")
        }

    }
}