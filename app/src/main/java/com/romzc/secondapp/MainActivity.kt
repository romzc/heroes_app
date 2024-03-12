package com.romzc.secondapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.romzc.secondapp.presentation.screen.SuperHeroActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val heroApp:Button = findViewById(R.id.toHeroList)
        heroApp.setOnClickListener { toListHero() }
    }

    private fun toListHero() {
        val intent = Intent(this, SuperHeroActivity::class.java)
        startActivity(intent)
    }
}