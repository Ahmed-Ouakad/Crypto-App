package com.example.finalcryptoapp.domain.model

import com.example.finalcryptoapp.data.remote.dto.CoinDetailDto
import com.example.finalcryptoapp.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId : String,
    val name : String,
    val description : String,
    val symbol : String,
    val rank : Int,
    val is_active : Boolean,
    var tags : List<String>,
    var team : List<TeamMember>

)

