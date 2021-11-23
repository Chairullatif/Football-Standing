package com.khoirullatif.footballstandings.ui.splashscreen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.khoirullatif.footballstandings.R
import com.khoirullatif.footballstandings.ui.login.LoginActivity
import com.khoirullatif.footballstandings.ui.onboarding.OnBoardingActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Default)
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        preferences = getPreferences(MODE_PRIVATE)
        val firstTime = preferences.getString("FirstTimeInstall", "")
        Log.d("SplashScreen", "firstTime: $firstTime")

        if (firstTime.equals("Yes")) {
            activityScope.launch {
                delay(3000)
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(0, 0)
                getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            }
        } else {
            val editor = preferences.edit()
            editor.putString("FirstTimeInstall", "Yes")
            editor.apply()
            activityScope.launch {
                delay(3000)
                val intent = Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(0, 0)
                getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            }
        }
    }
}