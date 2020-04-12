package com.example.polyclinic.polyclinic.android.polyclinic.patient.all

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.data.People
import com.example.polyclinic.polyclinic.android.data.PeopleInfo

class AllPatientAdapter(private val clickListener: AllPatientViewHolder.OnEditPersonClickListener) :
    RecyclerView.Adapter<AllPatientViewHolder>() {

    private var patients: List<People> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): AllPatientViewHolder {
        return AllPatientViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.polyclinic_patient, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllPatientViewHolder, position: Int) {
        holder.bind(patients[position], clickListener)
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