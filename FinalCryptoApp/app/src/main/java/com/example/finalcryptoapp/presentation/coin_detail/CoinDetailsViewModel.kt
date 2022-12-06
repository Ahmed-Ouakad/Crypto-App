package com.example.finalcryptoapp.presentation.coin_detail

import androidx.lifecycle.*
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import com.example.finalcryptoapp.common.Constants
import com.example.finalcryptoapp.common.Resource
import com.example.finalcryptoapp.data.local.CoinDetailDao
import com.example.finalcryptoapp.data.local.CoinDetailDatabase
import com.example.finalcryptoapp.data.remote.dto.TeamMember
import com.example.finalcryptoapp.domain.model.CoinDetail
import com.example.finalcryptoapp.domain.use_case.get_coin.GetCoinUseCase
import com.example.finalcryptoapp.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val db: CoinDetailDatabase


): ViewModel() {

    private val _state = MutableStateFlow(CoinDetailState())
    val state : StateFlow<CoinDetailState> = _state

fun triggerLiveData(coinId : String){
    getCoinDetail(coinId)
}
    private fun getCoinDetail(coinId : String){
        getCoinUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    var coin = result.data?.toCoinDetail()
                    val listOfTags : ArrayList<String> = ArrayList()
                    db.dao.getCoinTags(coinId).map { it.tags.forEach { elem-> listOfTags.add(elem.tag) } }
                    val list : List<String> = listOfTags
                    coin?.tags = list
                    var listOfMembers : ArrayList<TeamMember> = ArrayList()
                    db.dao.getTeamMembers(coinId).map { it.teamMembers.forEach { elem -> listOfMembers.add(elem.toTeamMember()) } }
                    var members : List<TeamMember> = listOfMembers
                    coin?.team = members
                    _state.value = CoinDetailState(
                        coin = coin
                    )
                }
                is Resource.Error ->{
                        if (_state.value.coin == null ){
                            _state.value = CoinDetailState(

                                error = result.message ?: "An unexpected error occurred",
                                coin = null
                            )
                        }else{
                            _state.value = CoinDetailState(

                                error = result.message ?: "An unexpected error occurred",
                                coin = result.data?.toCoinDetail()
                            )
                        }

                }
                is Resource.Loading -> {
                    if (_state.value.coin == null ){
                        _state.value = CoinDetailState(

                            isLoading = true,
                            coin = null
                        )
                    }else{
                        _state.value = CoinDetailState(

                            isLoading = true,
                            coin = result.data?.toCoinDetail()
                        )
                    }
                    _state.value = CoinDetailState(isLoading = true,coin = null?:result.data?.toCoinDetail()  )
                }
            }
        }.launchIn(viewModelScope)
    }

}
