package com.example.polyclinic.polyclinic.android.polyclinic.wards.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.data.Ward

class AllWardsAdapter : RecyclerView.Adapter<AllWardsViewHolder>() {

    private var wards: List<Ward> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllWardsViewHolder {
        return AllWardsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.polyclinic_ward, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return wards.size
    }

    override fun onBindViewHolder(holder: AllWardsViewHolder, position: Int) {
        holder.bind(wards[position])
    }

    fun setWards(wards: List<Ward>?) {
        if (wards == null || wards.isEmpty()) {
            return
        }

        this.wards = wards
        notifyDataSetChanged()
    }
}