package com.example.myapplicationlist.adapter

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.myapplicationlist.R
import com.example.myapplicationlist.model.RowData
import com.example.myapplicationlist.viewholder.CasarealViewHolder


class CasarealRecycleViewAdapter(private val list: MutableList<RowData>) :
    RecyclerView.Adapter<CasarealViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasarealViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return CasarealViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: CasarealViewHolder, position: Int) {
        holder.titleView.text = list[position].getTitle()
        holder.detailView.text = list[position].getDetail()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
