package com.example.finalcryptoapp.presentation.coin_list.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalcryptoapp.R
import com.example.finalcryptoapp.data.remote.dto.Tag
import kotlinx.android.synthetic.main.tag.view.*

class TagsAdapter(): RecyclerView.Adapter<TagsAdapter.ViewHolder>() {

    private var  listData : List<String>? = null

    fun setListData(list : List<String>?){
        listData = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagsAdapter.ViewHolder, position: Int) {
        holder.bind(listData!!.get(position))
    }

    override fun getItemCount(): Int {
        return if (listData == null) 0 else listData!!.size
    }
    inner  class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTag = view.tvTag
        fun bind(tag: String){
            tvTag.text = tag
        }
    }
}