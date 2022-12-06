package com.example.finalcryptoapp.presentation.coin_detail

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalcryptoapp.R
import com.example.finalcryptoapp.common.Resource
import com.example.finalcryptoapp.presentation.coin_list.component.TagsAdapter
import com.example.finalcryptoapp.presentation.coin_list.component.TeamAdapter
import com.google.android.material.internal.FlowLayout
import kotlinx.android.synthetic.main.fragment_coin_details.*
import kotlinx.android.synthetic.main.fragment_coin_details.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch




class CoinDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = CoinDetailsFragment()
    }
    val viewModel : CoinDetailsViewModel by activityViewModels()
    val args : CoinDetailsFragmentArgs by navArgs()
    lateinit var recyclerAdapter:TagsAdapter
    lateinit var adapter: TeamAdapter

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coin_details, container, false)
        viewModel.triggerLiveData(args.coinId)
        view.tvCoinDescription.movementMethod = ScrollingMovementMethod()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerAdapter = TagsAdapter()
        val layoutManager = GridLayoutManager(activity,3)
       view.rvTags.layoutManager = layoutManager
        view.rvTags.adapter = recyclerAdapter

        adapter = TeamAdapter()
        view.rvTeam.layoutManager = LinearLayoutManager(activity)
        view.rvTeam.adapter = adapter

        super.onViewCreated(view, savedInstanceState)
        val state = viewModel.state
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collectLatest { it ->
                    if(state.value.error.isNotBlank()&& state.value.coin == null ){
                        Toast.makeText(activity,it.error, Toast.LENGTH_LONG).show()
                        view.progressBarDetails.isVisible = false
                    }
                    else if (it.isLoading && state.value.coin == null){
                        view.progressBarDetails.isVisible = true
                    } else if(state.value.coin != null){
                        view.progressBarDetails.isVisible = false
                    view.tvCoinTitle.text = it.coin!!.rank.toString()+". " + it.coin!!.name + " ("+it.coin!!.symbol+")"
                    view.tvCoinDescription.text = it.coin!!.description

                        adapter.setListData(it.coin!!.team)
                        recyclerAdapter.setListData(it.coin!!.tags)
                        recyclerAdapter.notifyDataSetChanged()
                        adapter.notifyDataSetChanged()

                }

                }
            }
        }
    }
}