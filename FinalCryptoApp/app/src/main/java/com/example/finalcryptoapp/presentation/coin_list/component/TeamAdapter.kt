package com.example.finalcryptoapp.presentation.coin_list.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalcryptoapp.R
import com.example.finalcryptoapp.data.remote.dto.TeamMember
import kotlinx.android.synthetic.main.tag.view.*
import kotlinx.android.synthetic.main.team_member_item.view.*

class TeamAdapter(): RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    private var  listData : List<TeamMember>? = null

    fun setListData(list : List<TeamMember>?){
        listData = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_member_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData!!.get(position))
    }

    override fun getItemCount(): Int {
        return if (listData == null) 0 else listData!!.size
    }
    inner  class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name = view.memberName
        val pos = view.memberPosition
        fun bind(tag: TeamMember){
            name.text = tag.name
            pos.text = tag.position
        }
    }
}