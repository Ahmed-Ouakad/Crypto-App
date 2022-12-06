package com.example.finalcryptoapp.data.remote

import com.example.finalcryptoapp.data.remote.dto.CoinDetailDto
import com.example.finalcryptoapp.data.remote.dto.CoinDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
   suspend  fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinByid(@Path("coinId")coinId : String) : CoinDetailDto

}