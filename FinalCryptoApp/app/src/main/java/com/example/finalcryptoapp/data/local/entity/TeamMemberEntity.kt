package com.example.finalcryptoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalcryptoapp.data.remote.dto.TeamMember

@Entity
data class TeamMemberEntity (
        @PrimaryKey(autoGenerate = false)   val id: String,
        val name: String,
        val position: String,
        val coinId : String
        ){
        fun toTeamMember(): TeamMember{
                return TeamMember(
                        id = id,
                        name = name,
                        position = position
                )
        }
}