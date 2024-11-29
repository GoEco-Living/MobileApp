package com.ecoliving.mobile.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ecoliving.mobile.presentation.ui.onboard.OnboardFragment
import com.example.ecoliving.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}