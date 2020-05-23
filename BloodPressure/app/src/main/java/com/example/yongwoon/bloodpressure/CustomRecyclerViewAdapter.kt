package com.example.yongwoon.bloodpressure

import android.graphics.Color
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import io.realm.RealmResults



class CustomRecyclerViewAdapter(realmResults: RealmResults<BloodPress>): RecyclerView.Adapter<ViewHolder>() {
    private val rResult: RealmResults<BloodPress> = realmResults

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_result, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rResult.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bloodPress = rResult[position]
        holder.dateText?.text = DateFormat.format("yyyy/MM/dd kk:mm", bloodPress?.dateTime)
        holder.minMaxText?.text = "${bloodPress?.max.toString()}/${bloodPress?.min.toString()}"
        holder.pulseText?.text = bloodPress?.pulse.toString()
        holder.itemView.setBackgroundColor(if (position %2 == 0) Color.LTGRAY else Color.WHITE)
    }
}