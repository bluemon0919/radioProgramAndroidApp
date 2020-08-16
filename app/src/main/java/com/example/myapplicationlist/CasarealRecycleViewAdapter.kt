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

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasarealViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return CasarealViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: CasarealViewHolder, position: Int) {
        holder.titleView.text = list[position].getTitle()
        holder.deadlineView.text = list[position].getDeadline()

        // タップしたときの動作
        holder.titleView.setOnClickListener {
            listener.onItemClickListener(it, position, list[position].getWeekday())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener{
        fun onItemClickListener(view: View, position: Int, clickedText: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}
