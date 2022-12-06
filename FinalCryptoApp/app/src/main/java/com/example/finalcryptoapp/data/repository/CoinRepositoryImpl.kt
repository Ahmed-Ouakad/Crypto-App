package com.example.finalcryptoapp.data.repository

import com.example.finalcryptoapp.data.local.CoinDetailDatabase
import com.example.finalcryptoapp.data.remote.CoinPaprikaApi
import com.example.finalcryptoapp.data.remote.dto.CoinDetailDto
import com.example.finalcryptoapp.data.remote.dto.CoinDto
import com.example.finalcryptoapp.domain.repository.CoinRepository
import retrofit2.Call
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
private val api : CoinPaprikaApi,

) : CoinRepository{

    override suspend fun getCoins(): List<CoinDto> {
       return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
     return api.getCoinByid(coinId)
    }
}