package com.skintone.me.ui.readmore

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.skintone.me.R
import com.skintone.me.adapter.SliderAdapter
import com.skintone.me.databinding.ActivityReadMore3Binding
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

class ReadMoreActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityReadMore3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityReadMore3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager3)
        val dotsIndicator = findViewById<SpringDotsIndicator>(R.id.dotsIndicator3)

        val imageList = listOf(
            R.drawable.story7,
            R.drawable.story8,
            R.drawable.story9,
        )
        val adapter = SliderAdapter(imageList)
        viewPager.adapter = adapter
        dotsIndicator.setViewPager2(viewPager)

        binding.ivClose.setOnClickListener {
            onBackPressed()
        }

    }
}