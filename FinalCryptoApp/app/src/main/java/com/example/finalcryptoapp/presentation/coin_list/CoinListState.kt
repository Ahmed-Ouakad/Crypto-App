package com.example.finalcryptoapp.presentation.coin_list

import com.example.finalcryptoapp.domain.model.Coin

data class CoinListState(
    val isLoading : Boolean = false,
    val coins : List<Coin> = emptyList(),
    val error : String = ""
)
