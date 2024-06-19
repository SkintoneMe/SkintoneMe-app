package com.skintone.me.favo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var skintone: String,
    val image: String
)