package com.example.finalcryptoapp.domain.repository

import com.example.finalcryptoapp.data.remote.dto.CoinDetailDto
import com.example.finalcryptoapp.data.remote.dto.CoinDto
import retrofit2.Call

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId : String):CoinDetailDto

}