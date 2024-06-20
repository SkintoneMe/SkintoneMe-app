package com.skintone.me.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.skintone.me.R
import com.skintone.me.databinding.ActivityFavoriteGalleryBinding
import com.skintone.me.databinding.ActivityResultGalleryBinding

class ResultGalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultGalleryBinding
    private var colorIndex = 0
    private var jewelryColorIndex = 0
    private var paletteColorIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultGalleryBinding.inflate(layoutInflater)
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

        val imageUriString = intent.getStringExtra(DetailActivity.EXTRA_IMG_URI)
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
            ResourcesCompat.getColor(resources, R.color.mid_dark2, null),
        )

        val colorTexts = listOf(
            getString(R.string.mid_dark),
        )

        val jewelryColors = listOf(
            ResourcesCompat.getColor(resources, R.color.gold, null),
        )

        val jewerlyTexts = listOf(
            getString(R.string.gold),
        )

        val paletteColors = listOf(
            ResourcesCompat.getColor(resources, R.color.red_cherry, null),
            ResourcesCompat.getColor(resources, R.color.lavender, null),
            ResourcesCompat.getColor(resources, R.color.purple, null),
            ResourcesCompat.getColor(resources, R.color.dark_grey, null),
        )

        val codeColors = listOf(
            getString(R.string.cred_cherry),
            getString(R.string.clavender),
            getString(R.string.cpurple),
            getString(R.string.cdark_grey),
        )

        val nameColors = listOf(
            getString(R.string.red_cherry),
            getString(R.string.lavender),
            getString(R.string.purple),
            getString(R.string.dark_grey),
        )

        // Set sequential color to TypeSkin
        val typeSkinIndex = colorIndex % colors.size
        setImageViewColor(binding.ivTypeSkin, colors[typeSkinIndex])
        binding.tvColor.text = colorTexts[typeSkinIndex]
        colorIndex++

        // Set sequential color to ColorJewelry
        var jewelryColorIndex = jewelryColorIndex % jewelryColors.size
        setImageViewColor(binding.ivColorJewerly, jewelryColors[jewelryColorIndex])
        binding.tvColor2.text = jewerlyTexts[jewelryColorIndex]
        jewelryColorIndex++

        // Set sequential color to color_palette1
        val paletteColorIndex1 = paletteColorIndex % paletteColors.size
        setImageViewColor(binding.ivColorPalette1, paletteColors[paletteColorIndex1])
        binding.tvColorName1.text = nameColors[paletteColorIndex1]
        binding.tvColorCode1.text = codeColors[paletteColorIndex1]
        paletteColorIndex++

        // Set sequential color to color_palette2
        val paletteColorIndex2 = paletteColorIndex % paletteColors.size
        setImageViewColor(binding.ivColorPalette2, paletteColors[paletteColorIndex2])
        binding.tvColorName2.text = nameColors[paletteColorIndex2]
        binding.tvColorCode2.text = codeColors[paletteColorIndex2]
        paletteColorIndex++

        // Set sequential color to iv_color_palette3
        val paletteColorIndex3 = paletteColorIndex % paletteColors.size
        setImageViewColor(binding.ivColorPalette3, paletteColors[paletteColorIndex3])
        binding.tvColorName3.text = nameColors[paletteColorIndex3]
        binding.tvColorCode3.text = codeColors[paletteColorIndex3]
        paletteColorIndex++

        // Set sequential color to iv_color_palette4
        val paletteColorIndex4 = paletteColorIndex % paletteColors.size
        setImageViewColor(binding.ivColorPalette4, paletteColors[paletteColorIndex4])
        binding.tvColorName4.text = nameColors[paletteColorIndex4]
        binding.tvColorCode4.text = codeColors[paletteColorIndex4]
        paletteColorIndex++

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