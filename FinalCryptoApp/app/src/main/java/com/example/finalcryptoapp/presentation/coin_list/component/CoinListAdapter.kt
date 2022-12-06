package com.example.finalcryptoapp.presentation.coin_list.component

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.finalcryptoapp.MainActivity
import com.example.finalcryptoapp.R
import com.example.finalcryptoapp.domain.model.Coin
import com.example.finalcryptoapp.presentation.coin_list.CoinListScreenFragment
import com.example.finalcryptoapp.presentation.coin_list.CoinListScreenFragmentDirections
import kotlinx.android.synthetic.main.fragment_coin_list_item.view.*

class CoinListAdapter(

)  : RecyclerView.Adapter<CoinListAdapter.ViewHolder>(){

    private var  listData : List<Coin>? = null
    fun setListData(list : List<Coin>?){
        listData = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_coin_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinListAdapter.ViewHolder, position: Int) {
        holder.bind(listData!!.get(position))
    }

    override fun getItemCount(): Int {
        return if (listData == null) 0 else listData!!.size
    }

    inner  class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvName = view.tvName
        val tvIsActive = view.tvIsActive

        fun bind(data: Coin){
            tvName.text = data.rank.toString()+". " +data.name + " ("+data.symbol+")"
           tvName.setOnClickListener{
                //Toast.makeText(itemView.context,"this is the coin name -> "+ data.id,Toast.LENGTH_LONG).show()
                val action = CoinListScreenFragmentDirections.actionCoinListScreenFragmentToCoinDetailsFragment(data.id)

                Navigation.findNavController(it).navigate(action)
            }
            if (data.is_active){
                tvIsActive.text = "active"
                tvIsActive.setTextColor(Color.GREEN)
            }else{
                tvIsActive.text = "inactive"
                tvIsActive.setTextColor(Color.RED)
            }

        }
    }

}