package com.example.finalcryptoapp.presentation.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalcryptoapp.R
import com.example.finalcryptoapp.domain.model.Coin
import com.example.finalcryptoapp.presentation.coin_list.component.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_coin_list_screen.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinListScreenFragment : Fragment() {

    companion object {
        fun newInstance() = CoinListScreenFragment()
    }

    lateinit var recyclerAdapter: CoinListAdapter
    lateinit var listCoins : List<Coin>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coin_list_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel : CoinListScreenViewModel by activityViewModels ()

        recyclerAdapter = CoinListAdapter()

        view.recycler.layoutManager = LinearLayoutManager(activity)
        view.recycler.adapter = recyclerAdapter
        val state = viewModel.state
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collectLatest { it ->

                     if(state.value.error.isNotBlank()){
                        Toast.makeText(activity,it.error, Toast.LENGTH_LONG).show()
                        view.progressBar.isVisible = false
                    }
                    else if (state.value.isLoading){
                        view.progressBar.isVisible = true
                    } else {
                         recyclerAdapter.setListData(it.coins)
                         listCoins = it.coins
                         recyclerAdapter.notifyDataSetChanged()
                         view.progressBar.isVisible = false
                     }
                }
            }
        }

    }


}