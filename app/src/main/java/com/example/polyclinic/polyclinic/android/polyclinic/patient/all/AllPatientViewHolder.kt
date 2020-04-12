package com.example.polyclinic.polyclinic.android.polyclinic.patient.all

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.data.People

class AllPatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val personFioTextView = itemView.findViewById(R.id.person_fio) as TextView
    private val personDiagnosisTextView = itemView.findViewById(R.id.person_diagnosis) as TextView
    private val personWardTextView = itemView.findViewById(R.id.person_ward) as TextView
    private val editPersonTextView = itemView.findViewById(R.id.edit_person_tv) as TextView
    private var id: Int = 0

    fun bind(patient: People, editPersonClickListener: OnEditPersonClickListener) {
        this.id = patient.id
        personFioTextView.text = patient.lastName
            .plus(" ")
            .plus(patient.firstName)
            .plus(" ")
            .plus(patient.fatherName)

        personDiagnosisTextView.text = patient.diagnosis.name
        personWardTextView.text = patient.ward.name
        editPersonTextView.setOnClickListener {
            editPersonClickListener.onEditPersonClick(
                id,
                personFioTextView.text as String,
                personDiagnosisTextView.text as String,
                personWardTextView.text as String,
                adapterPosition
            )
        }
    }

    interface OnEditPersonClickListener {
        fun onEditPersonClick(id: Int, fio: String, diagnosis: String, ward: String, position: Int)
    }
}