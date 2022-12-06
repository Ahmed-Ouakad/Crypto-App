package com.example.finalcryptoapp.di

import android.app.Application
import androidx.room.Room
import com.example.finalcryptoapp.common.Constants
import com.example.finalcryptoapp.data.local.CoinDetailDatabase
import com.example.finalcryptoapp.data.remote.CoinPaprikaApi
import com.example.finalcryptoapp.data.repository.CoinRepositoryImpl
import com.example.finalcryptoapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun providePaprikaApi():CoinPaprikaApi{
      return Retrofit.Builder()
          .baseUrl(Constants.Base_Url)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
          .create(CoinPaprikaApi :: class.java)

  }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi,db : CoinDetailDatabase):CoinRepository{
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCoinDetailDatabase(app:Application):CoinDetailDatabase{
        return Room.databaseBuilder(
            app,CoinDetailDatabase::class.java,"coin_db"
        ).build()
    }

}