package com.wuhome.firebaseauthdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        Toast.makeText(
            this@MainActivity,
            "Your userID is" + userId + "Your emailId is" + emailId,
            Toast.LENGTH_SHORT
        ).show()
    }
}