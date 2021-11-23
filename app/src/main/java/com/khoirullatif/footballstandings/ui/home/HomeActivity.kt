package com.khoirullatif.footballstandings.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.khoirullatif.footballstandings.R

class HomeActivity : AppCompatActivity() {

    private lateinit var tv_title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        tv_title = findViewById(R.id.tv_title_home)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        val navController = findNavController(R.id.fragmentContainerView)

        navController.addOnDestinationChangedListener{_, destination,_ ->
            if (destination.id == R.id.leaguesFragment) {
                tv_title.text = "Leagues"
            } else {
                tv_title.text = "Profile"
            }
        }
        bottomNavigationView.setupWithNavController(navController)
    }
}