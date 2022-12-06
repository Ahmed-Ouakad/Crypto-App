package com.example.finalcryptoapp.data.remote.dto

import com.example.finalcryptoapp.data.local.entity.TeamMemberEntity

data class TeamMember(
    val id: String,
    val name: String,
    val position: String
){
    fun toTeamMemberEntity(coinId: String): TeamMemberEntity {

        return TeamMemberEntity(

            id = id,
            position = position,
            coinId = coinId ,
            name = name
        )
    }
}