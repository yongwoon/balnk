package com.example.yongwoon.bloodpressure

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.one_result.view.*

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var dateText: TextView? = null
    var minMaxText: TextView? = null
    var pulseText: TextView? = null

    init {
        // view holder の property と layout の view の対応
        dateText = itemView.dateText
        minMaxText = itemView.minMaxText
        pulseText = itemView.pulseText
    }
}