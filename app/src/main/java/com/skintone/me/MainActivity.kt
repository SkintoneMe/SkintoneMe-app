package com.skintone.me

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.skintone.me.databinding.ActivityMainBinding
import com.skintone.me.ui.camera.CameraActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, navDestination, _ ->
            Handler(Looper.getMainLooper()).post {
                when (navDestination.id) {
                    R.id.navigation_home, R.id.navigation_profile -> {
                        binding.navView.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.navView.visibility = View.GONE
                    }
                }
            }
        }

        binding.navView.setupWithNavController(navController)

        // Set up navigation item selection listener
        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_camera -> {
                    // Start CameraActivity
                    val intent = Intent(this, CameraActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                    // Allow the NavController to handle other selections
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true
                }
            }
        }
    }
}
