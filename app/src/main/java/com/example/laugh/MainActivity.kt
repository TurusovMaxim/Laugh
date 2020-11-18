package com.example.laugh

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.laugh.network.NetworkService
import com.example.laugh.network.request.AuthRequest
import com.example.laugh.network.response.UserInfoBaseResponse
import com.example.laugh.network.response.UserInfoResponse
import com.example.laugh.retofit.ServiceBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}