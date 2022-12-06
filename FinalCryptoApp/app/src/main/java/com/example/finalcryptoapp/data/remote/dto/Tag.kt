package com.example.finalcryptoapp.data.remote.dto

import com.example.finalcryptoapp.data.local.entity.TagEntity

data class Tag(
    val coin_counter: Int,
    val ico_counter: Int,
    val id: String,
    val name: String
){
    fun toTagEntity(coinId : String): TagEntity {

        return TagEntity(
            tag = name,
            coinId = coinId,
            id = 0
        )
    }
}