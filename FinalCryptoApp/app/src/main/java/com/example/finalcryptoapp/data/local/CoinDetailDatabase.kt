package com.example.finalcryptoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalcryptoapp.data.local.entity.CoinDetailEntity
import com.example.finalcryptoapp.data.local.entity.TagEntity
import com.example.finalcryptoapp.data.local.entity.TeamMemberEntity

@Database(
    entities = [
        CoinDetailEntity::class,
        TagEntity::class,
        TeamMemberEntity::class
    ],
    version = 1
)
abstract class CoinDetailDatabase : RoomDatabase(){
    abstract val dao : CoinDetailDao
}