package com.example.polyclinic.polyclinic.android.polyclinic.wards.all

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.data.Ward

class AllWardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val wardNameTextView: TextView = itemView.findViewById(R.id.ward_name)
    private val wardMaxCountTextView: TextView = itemView.findViewById(R.id.ward_max_count)
    private val recyclerPatient: RecyclerView = itemView.findViewById(R.id.ward_patient_recycler)
    private val adapter: WardPatientAdapter = WardPatientAdapter()

    fun bind(ward: Ward) {
        wardNameTextView.text = ward.name
        wardMaxCountTextView.text = ward.maxCount.toString()

        if (ward.patients.isEmpty()) {
            recyclerPatient.visibility = View.GONE
        } else {
            recyclerPatient.visibility = View.VISIBLE
            recyclerPatient.adapter = adapter
            recyclerPatient.layoutManager = LinearLayoutManager(itemView.context)
            adapter.setPatients(ward.patients)
        }
    }
}