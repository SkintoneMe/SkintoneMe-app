package com.skintone.me.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.skintone.me.MainActivity
import com.skintone.me.R
import com.skintone.me.databinding.ActivityDetailBinding
import com.skintone.me.ui.home.FavoriteActivity
import kotlin.random.Random

class DetailActivity : AppCompatActivity() {

    private var isEdit = false

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivClose.setOnClickListener {
            onBackPressed()
        }

        binding.btnAddFavorites.setOnClickListener {
            val isFavorite = it.tag as? Boolean ?: false

            if (isFavorite) {
                binding.btnAddFavorites.text = getString(R.string.add_result)
                binding.btnAddFavorites.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_love2, 0, 0, 0)
                Toast.makeText(this, "Remove Result", Toast.LENGTH_SHORT).show()
            } else {
                binding.btnAddFavorites.text = getString(R.string.remove_result)
                binding.btnAddFavorites.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_love3, 0, 0, 0)
                Toast.makeText(this, "Save Result", Toast.LENGTH_SHORT).show()
            }
            it.tag = !isFavorite
        }

        val imageUriString = intent.getStringExtra(EXTRA_IMG_URI)
        imageUriString?.let {
            val imageUri = Uri.parse(it)
            binding.circleImageView.setImageURI(imageUri)
        }  ?: run {
            binding.circleImageView.setImageResource(R.drawable.ic_account)
        }

        setRandomColorToImageView()

    }

    private fun setRandomColorToImageView() {

        //type skintone
        val colors = listOf(
            ResourcesCompat.getColor(resources, R.color.light, null),
            ResourcesCompat.getColor(resources, R.color.mid_light, null),
            ResourcesCompat.getColor(resources, R.color.mid_dark, null),
            ResourcesCompat.getColor(resources, R.color.dark, null)
        )

        val colorTexts = listOf(
            getString(R.string.light),
            getString(R.string.mid_light),
            getString(R.string.mid_dark),
            getString(R.string.dark)
        )

        val randomIndex = Random.nextInt(colors.size)
        val randomText = colorTexts[randomIndex]

        val randomColor = colors[Random.nextInt(colors.size)]
        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint().apply {
            color = randomColor
            isAntiAlias = true
        }

        val centerX = bitmap.width / 2
        val centerY = bitmap.height / 2
        val radius = (bitmap.width / 2).toFloat()
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius, paint)
        binding.ivTypeSkin.setImageBitmap(bitmap)

        binding.tvColor.text = randomText

        //jewelry color

        val jewelryColors = listOf(
            ResourcesCompat.getColor(resources, R.color.gold, null),
            ResourcesCompat.getColor(resources, R.color.silver, null)
        )

        val jewerlyTexts = listOf(
            getString(R.string.gold),
            getString(R.string.silver)
        )

        val randomIndex2 = Random.nextInt(jewelryColors.size)
        val randomText2 = jewerlyTexts[randomIndex2]

        val randomColor2 = jewelryColors[Random.nextInt(jewelryColors.size)]
        val bitmap2 = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        val canvas2 = Canvas(bitmap2)
        val paint2 = Paint().apply {
            color = randomColor2
            isAntiAlias = true
        }
        val centerX2 = bitmap.width / 2
        val centerY2 = bitmap.height / 2
        val radius2 = (bitmap.width / 2).toFloat()
        canvas2.drawCircle(centerX2.toFloat(), centerY2.toFloat(), radius2, paint2)
        binding.ivColorJewerly.setImageBitmap(bitmap2)

        binding.tvColor2.text = randomText2

    }

    companion object {
        const val EXTRA_IMG_URI = "extra_img_uri"
    }


}