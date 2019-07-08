package com.example.weekend5

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View)  =
        when (view.id) {
            R.id.btnFilterAct-> {
                startActivity(Intent(this, FilterEmployeeActivity::class.java))
            }
            else -> {
            }
        }

}
