package com.example.polyclinic.polyclinic.android.polyclinic.wards.all

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.data.People

open class WardPatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val patientFioTextView: TextView = itemView.findViewById(R.id.patient_fio)

    open fun bind(patient: People) {
        patientFioTextView.text = patient.lastName
            .plus(" ")
            .plus(patient.firstName)
            .plus(" ")
            .plus(patient.fatherName)
    }
}