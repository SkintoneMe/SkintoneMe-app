package com.skintone.me.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.skintone.me.R
import com.skintone.me.databinding.ActivityDetailBinding
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

        val jewelryColors = listOf(
            ResourcesCompat.getColor(resources, R.color.gold, null),
            ResourcesCompat.getColor(resources, R.color.silver, null)
        )

        val jewerlyTexts = listOf(
            getString(R.string.gold),
            getString(R.string.silver)
        )

        val paletteColors = listOf(
            ResourcesCompat.getColor(resources, R.color.palette1, null),
            ResourcesCompat.getColor(resources, R.color.palette2, null),
            ResourcesCompat.getColor(resources, R.color.palette3, null),
            ResourcesCompat.getColor(resources, R.color.palette4, null),
            ResourcesCompat.getColor(resources, R.color.palette5, null),
            ResourcesCompat.getColor(resources, R.color.palette6, null),
            ResourcesCompat.getColor(resources, R.color.palette7, null),
            ResourcesCompat.getColor(resources, R.color.palette8, null),
        )

        val codeColors = listOf(
            getString(R.string.palette1),
            getString(R.string.palette2),
            getString(R.string.palette3),
            getString(R.string.palette4),
            getString(R.string.palette5),
            getString(R.string.palette6),
            getString(R.string.palette7),
            getString(R.string.palette8),
        )

        val nameColors = listOf(
            getString(R.string.white),
            getString(R.string.cherry),
            getString(R.string.navy),
            getString(R.string.mauve),
            getString(R.string.cosmic_Latte),
            getString(R.string.red_crimson),
            getString(R.string.red_cherry),
            getString(R.string.lavender),
        )

        // Set random color to TypeSkin
        val randomIndex = Random.nextInt(colors.size)
        val randomText = colorTexts[randomIndex]
        setImageViewColor(binding.ivTypeSkin, colors[randomIndex])
        binding.tvColor.text = randomText


        // Set random color to ColorJewerly
        val randomIndex2 = Random.nextInt(jewelryColors.size)
        val randomText2 = jewerlyTexts[randomIndex2]
        setImageViewColor(binding.ivColorJewerly, jewelryColors[randomIndex2])
        binding.tvColor2.text = randomText2


        // Set random color to color_palette1
        val randomIndex3 = Random.nextInt(paletteColors.size)
        val randomText3 = nameColors[randomIndex3]
        val randomCode3 = codeColors[randomIndex3]
        setImageViewColor(binding.ivColorPalette1, paletteColors[randomIndex3])
        binding.tvColorName1.text = randomText3
        binding.tvColorCode1.text = randomCode3

        // Set random color to color_palette2
        val randomIndex4 = Random.nextInt(paletteColors.size)
        val randomText4 = nameColors[randomIndex4]
        val randomCode4 = codeColors[randomIndex4]
        setImageViewColor(binding.ivColorPalette2, paletteColors[randomIndex4])
        binding.tvColorName2.text = randomText4
        binding.tvColorCode2.text = randomCode4

        // Set random color to iv_color_palette3
        val randomIndex5 = Random.nextInt(paletteColors.size)
        val randomText5 = nameColors[randomIndex5]
        val randomCode5 = codeColors[randomIndex5]
        setImageViewColor(binding.ivColorPalette3, paletteColors[randomIndex5])
        binding.tvColorName3.text = randomText5
        binding.tvColorCode3.text = randomCode5

        // Set random color to iv_color_palette4
        val randomIndex6 = Random.nextInt(paletteColors.size)
        val randomText6 = nameColors[randomIndex6]
        val randomCode6 = codeColors[randomIndex6]
        setImageViewColor(binding.ivColorPalette4, paletteColors[randomIndex6])
        binding.tvColorName4.text = randomText6
        binding.tvColorCode4.text = randomCode6

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

    companion object {
        const val EXTRA_IMG_URI = "extra_img_uri"
    }


}