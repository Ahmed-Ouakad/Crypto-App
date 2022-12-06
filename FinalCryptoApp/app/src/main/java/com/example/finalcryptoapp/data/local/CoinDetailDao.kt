package com.example.finalcryptoapp.data.local

import androidx.room.*
import com.example.finalcryptoapp.data.local.entity.CoinDetailEntity
import com.example.finalcryptoapp.data.local.entity.TagEntity
import com.example.finalcryptoapp.data.local.entity.TeamMemberEntity
import com.example.finalcryptoapp.data.local.relation.CoinWithTags
import com.example.finalcryptoapp.data.local.relation.CoinWithTeamMember

@Dao
interface CoinDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamMember(teamMember: TeamMemberEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag : TagEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDetail(coinDetail: CoinDetailEntity)

    @Transaction
    @Query("SELECT * FROM coindetailentity WHERE coinId = :coinId")
    suspend fun getCoinTags(coinId : String) : List<CoinWithTags>

    @Transaction
    @Query("SELECT * FROM coindetailentity WHERE coinId = :coinId")
    suspend fun getCoinDetail(coinId : String) : CoinDetailEntity

    @Transaction
    @Query("SELECT * FROM coindetailentity WHERE coinId = :coinId")
    suspend fun getTeamMembers(coinId: String):List<CoinWithTeamMember>

}