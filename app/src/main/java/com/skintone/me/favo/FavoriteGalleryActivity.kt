package com.skintone.me.favo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.skintone.me.R
import com.skintone.me.databinding.ActivityFavoriteGalleryBinding

class FavoriteGalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteGalleryBinding
    private var skintoneIndex = 0
    private var colorPaletteIndex = 0
    private var jewerlyColorIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivClose.setOnClickListener {
            onBackPressed()
        }

        setColor()

    }

    private fun setColor(){

        val skintoneColor = listOf(
            ResourcesCompat.getColor(resources, R.color.mid_dark2, null),
        )

        val paletteColor = listOf(
            ResourcesCompat.getColor(resources, R.color.red_cherry, null)
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