package com.example.technopat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val but1 =findViewById<Button>(R.id.rembut)
        but1.setOnClickListener{
            val intent1 = Intent(this, remainder::class.java)
            startActivity(intent1)
        }
    }
}