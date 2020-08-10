package com.example.myapplicationlist.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import com.example.myapplicationlist.R;


class CasarealViewHolder(itemView: View) : ViewHolder(itemView) {
    var titleView: TextView
    var detailView: TextView

    init {
        titleView = itemView.findViewById(R.id.title)
        detailView = itemView.findViewById(R.id.detail)
    }
}