package com.shamilov.payda.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shamilov.payda.R

/**
 * Created by Shamilov on 20.05.2020
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        //So that the activity is not re created
        navController.addOnDestinationChangedListener { _, destination, _ ->
            for (menuItem in navView.menu.iterator()) {
                menuItem.isEnabled = true
            }

            val menu = navView.menu.findItem(destination.id)
            menu?.isEnabled = false
        }

        navView.setupWithNavController(navController)
    }
}
