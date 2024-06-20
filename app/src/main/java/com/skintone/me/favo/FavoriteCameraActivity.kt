package com.skintone.me.favo

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.skintone.me.R
import com.skintone.me.databinding.ActivityFavoritCameraBinding
import com.skintone.me.ui.ResultCameraActivity

class FavoriteCameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritCameraBinding
    private var skintoneIndex = 0
    private var colorPaletteIndex = 0
    private var jewerlyColorIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivClose.setOnClickListener {
            onBackPressed()
        }

//        binding.frameLayout3.setOnClickListener {
//            startActivity(Intent(this, ResultCameraActivity::class.java))
//        }

        setColor()

    }

    private fun setColor(){

        val skintoneColor = listOf(
            ResourcesCompat.getColor(resources, R.color.light, null),
        )

        val paletteColor = listOf(
            ResourcesCompat.getColor(resources, R.color.soft_pink, null)
        )

        val jewerlyColor = listOf(
            ResourcesCompat.getColor(resources, R.color.gold, null)
        )

        val skinToneIndexs = skintoneIndex % skintoneColor.size
        setImageViewColor(binding.ivCircleSkintone, skintoneColor[skinToneIndexs])

        val paletteColors = colorPaletteIndex % paletteColor.size
        setImageViewColor(binding.ivCircleColorpalette, paletteColor[paletteColors])

        val jewerlyColors = jewerlyColorIndex % jewerlyColor.size
        setImageViewColor(binding.ivCircleJewerlycolor, jewerlyColor[jewerlyColors])


    }

    private fun setImageViewColor(imageView: ImageView, color: Int) {
        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint().apply {
            this.color = color
            isAntiAlias = true
        }

        val centerX = bitmap.width / 2
        val centerY = bitmap.height / 2
        val radius = (bitmap.width / 2).toFloat()
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius, paint)
        imageView.setImageBitmap(bitmap)
    }

}