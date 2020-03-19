package com.example.polyclinic.polyclinic.android.polyclinic.patient.all

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.data.People

class AllPatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val personFioTextView: TextView = itemView.findViewById(R.id.person_fio)
    private val personDiagnosisTextView: TextView = itemView.findViewById(R.id.person_diagnosis)
    private val personWardTextView: TextView = itemView.findViewById(R.id.person_ward)

    fun bind(patient: People) {
        personFioTextView.text = patient.lastName
            .plus(" ")
            .plus(patient.firstName)
            .plus(" ")
            .plus(patient.fatherName)

        personDiagnosisTextView.text = patient.diagnosis.name
        personWardTextView.text = patient.ward.name
    }
}