package com.example.polyclinic.polyclinic.android.polyclinic.wards.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.data.People

class WardPatientAdapter : RecyclerView.Adapter<WardPatientViewHolder>() {

    private var patients: List<People> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WardPatientViewHolder {
        return WardPatientViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.polyclinic_ward_patient, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WardPatientViewHolder, position: Int) {
        holder.bind(patients[position])
    }

    override fun getItemCount(): Int {
        return patients.size
    }

    fun setPatients(patients: List<People>?) {
        if (patients == null || patients.isEmpty()) {
            return
        }

        this.patients = patients
        notifyDataSetChanged()
    }
}