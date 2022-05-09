package com.serproteam.gmemory.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.serproteam.gmemory.domain.Entitys.Cartas

abstract class BaseViewHolder<T>(itemView: View):RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Cartas, position: Int)
}