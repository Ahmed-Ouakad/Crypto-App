package com.example.finalcryptoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalcryptoapp.data.local.CoinDetailDao
import com.example.finalcryptoapp.domain.model.CoinDetail
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

@Entity
data class CoinDetailEntity(
@PrimaryKey(autoGenerate = false) val coinId:String,
val name:String,
val description : String,
val symbol : String,
val rank : Int,
val is_active : Boolean,

){
    fun toCoinDetail():CoinDetail{
        return CoinDetail(
            name =name,
            coinId = coinId,
            description = description,
            symbol =  symbol,
            rank = rank,
            is_active = is_active,
            tags = emptyList(),
            team = emptyList()
        )
    }

}
