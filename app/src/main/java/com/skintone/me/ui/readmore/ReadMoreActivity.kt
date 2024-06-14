package com.skintone.me.ui.readmore

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.skintone.me.R
import com.skintone.me.adapter.SliderAdapter
import com.skintone.me.databinding.ActivityReadMoreBinding
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

class ReadMoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadMoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityReadMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val dotsIndicator = findViewById<SpringDotsIndicator>(R.id.dotsIndicator)

        val imageList = listOf(
            R.drawable.story1,
            R.drawable.story2,
            R.drawable.story3,
        )
        val adapter = SliderAdapter(imageList)
        viewPager.adapter = adapter
        dotsIndicator.setViewPager2(viewPager)

        binding.ivClose.setOnClickListener {
            onBackPressed()
        }

    }


}