package com.example.finalcryptoapp.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalcryptoapp.common.Resource
import com.example.finalcryptoapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListScreenViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState(isLoading = true))
    val state: StateFlow<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach {result ->
            when(result){
                is Resource.Success ->{
                    _state.value = CoinListState(
                        coins = result.data ?: emptyList(), isLoading = false,error = ""
                    )
                }
                is Resource.Error ->{
                    _state.value = CoinListState(
                        error = result.message ?: "An unexpected error occurred", isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _state.value = CoinListState(isLoading = true, error = "")
                }
            }
        }.launchIn(viewModelScope)
    }

}