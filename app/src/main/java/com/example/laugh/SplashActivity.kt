package com.example.laugh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toTheNewPage()
    }

    private fun toTheNewPage() {
        val handler = Handler()

        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        },300)
    }
}