package com.example.finalcryptoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TagEntity(
    val tag : String,
    val coinId : String,
    @PrimaryKey(autoGenerate = true) val id : Int
)
