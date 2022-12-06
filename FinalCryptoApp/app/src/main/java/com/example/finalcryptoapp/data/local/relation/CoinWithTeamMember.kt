package com.example.finalcryptoapp.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.finalcryptoapp.data.local.entity.CoinDetailEntity
import com.example.finalcryptoapp.data.local.entity.TeamMemberEntity

data class CoinWithTeamMember(
    @Embedded val coinDetail : CoinDetailEntity,
    @Relation(
        parentColumn = "coinId",
        entityColumn = "coinId"
    )
    val teamMembers : List<TeamMemberEntity>
)
