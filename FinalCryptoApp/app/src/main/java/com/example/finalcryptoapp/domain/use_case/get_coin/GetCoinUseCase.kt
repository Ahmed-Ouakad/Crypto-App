package com.example.finalcryptoapp.domain.use_case.get_coin

import com.example.finalcryptoapp.common.Resource
import com.example.finalcryptoapp.data.local.CoinDetailDatabase
import com.example.finalcryptoapp.data.local.entity.CoinDetailEntity
import com.example.finalcryptoapp.data.remote.dto.toCoin
import com.example.finalcryptoapp.data.remote.dto.toCoinDetail
import com.example.finalcryptoapp.data.remote.dto.toCoinDetailEntity
import com.example.finalcryptoapp.domain.model.Coin
import com.example.finalcryptoapp.domain.model.CoinDetail
import com.example.finalcryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val db : CoinDetailDatabase
) {
    operator fun invoke(coinId :String): Flow<Resource<CoinDetailEntity>> = flow{
        var coinDet : CoinDetailEntity ?
        coinDet = db.dao.getCoinDetail(coinId)
        emit(Resource.Loading(data = coinDet))
        try {
            emit(Resource.Loading())
            val resp = repository.getCoinById(coinId)
             coinDet = resp.toCoinDetailEntity()
            db.dao.insertCoinDetail(coinDet)
             resp.tags.forEach {
                db.dao.insertTag(it.toTagEntity(coinId))
            }
            resp.team.forEach {
                db.dao.insertTeamMember(it.toTeamMemberEntity(resp.id))
            }

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?: "An unexpected message occured",coinDet ))
        }catch (e : IOException){
            emit(Resource.Error("Please check your internet connection",coinDet))
        }
        emit(Resource.Success(db.dao.getCoinDetail(coinId)))
    }
}