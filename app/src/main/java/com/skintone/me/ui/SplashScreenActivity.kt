package com.skintone.me.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.skintone.me.MainActivity
import com.skintone.me.R
import com.skintone.me.database.PreferenceManager
import com.skintone.me.database.dataStore
import com.skintone.me.databinding.ActivitySplashScreenBinding
import com.skintone.me.introslider.IntroSliderActivity1
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private val splashTimeOut: Long = 3000
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        preferenceManager = PreferenceManager.getInstance(dataStore)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launch {
                preferenceManager.getSession().collect{user->
                    Log.d("LOGIN", "onCreate: ${user.toString()}")
                    if (user.isLogin) {
                        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        val intent = Intent(this@SplashScreenActivity, IntroSliderActivity1::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }, splashTimeOut)

    }
}