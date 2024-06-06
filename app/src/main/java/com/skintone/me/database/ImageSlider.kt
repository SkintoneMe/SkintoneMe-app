package com.skintone.me.database

import androidx.annotation.ColorInt

data class ImageSlider(
    val title: String? = null,
    val imageResId: Int? = null,
    @ColorInt val backgroundColor: Int? = null
)