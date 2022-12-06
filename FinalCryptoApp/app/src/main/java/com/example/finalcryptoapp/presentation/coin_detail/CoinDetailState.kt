package com.example.finalcryptoapp.presentation.coin_detail

import com.example.finalcryptoapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading : Boolean = false,
    val error: String = "",
    val coin : CoinDetail? = null
)
