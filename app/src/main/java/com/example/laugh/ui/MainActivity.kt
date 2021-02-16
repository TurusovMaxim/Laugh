package com.example.laugh.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.laugh.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var memeFeedFragment: MemeFeedFragment
    private lateinit var createMemeFragment: CreateMemeFragment
    private lateinit var profileFragment: ProfileFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragments()

        bottomNavigationView.setOnNavigationItemSelectedListener {item ->
            when(item.itemId) {

                R.id.action_feed -> memeFeed()

                R.id.action_create -> createMeme()

                R.id.action_profile -> profile()

                else -> false
            }
        }

    }

    private fun initFragments() {
        bottomNavigationView = findViewById(R.id.btn_nav_view)

        memeFeedFragment = MemeFeedFragment()
        createMemeFragment = CreateMemeFragment()
        profileFragment = ProfileFragment()
    }

    private fun memeFeed(): Boolean{

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_feed, memeFeedFragment).commit()

        return true
    }

    private fun createMeme(): Boolean{

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_feed, createMemeFragment).commit()

        return true
    }


    private fun profile(): Boolean {

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_feed, profileFragment).commit()

        return true
    }
}