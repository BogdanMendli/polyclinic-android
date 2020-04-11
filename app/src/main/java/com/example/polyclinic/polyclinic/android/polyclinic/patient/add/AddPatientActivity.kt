package com.example.polyclinic.polyclinic.android.polyclinic.patient.add

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.polyclinic.polyclinic.android.HomeButtonToolbarActivity
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.api.PolyclinicApiService
import com.example.polyclinic.polyclinic.android.api.RetrofitFactory
import com.example.polyclinic.polyclinic.android.data.Diagnosis
import com.example.polyclinic.polyclinic.android.data.People
import com.example.polyclinic.polyclinic.android.data.Result
import com.example.polyclinic.polyclinic.android.data.Ward
import kotlinx.android.synthetic.main.activity_add_patient.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AddPatientActivity : HomeButtonToolbarActivity(), View.OnClickListener {

    private var lastNameView: AutoCompleteTextView? = null
    private var firstNameView: AutoCompleteTextView? = null
    private var fatherNameView: AutoCompleteTextView? = null
    private var diagnosisNameView: AutoCompleteTextView? = null
    private var wardNameView: AutoCompleteTextView? = null
    private var addPatientButton: Button? = null
    private var errorTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lastNameView = last_name_input
        firstNameView = first_name_input
        fatherNameView = father_name_input
        diagnosisNameView = diagnosis_name_input
        wardNameView = ward_name_input
        errorTextView = error_tv
        addPatientButton = add_patient_button
        addPatientButton?.setOnClickListener(this)
        setToolbarTitle(R.string.add_person_add_patient_text)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_patient
    }

    private fun doRequestAddPatient(context: Context, patient: People) {
        RetrofitFactory.create(PolyclinicApiService::class.java).addPatient(patient)
            .enqueue(object :
                Callback<Result> {
                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Toast.makeText(context, "something went wrong ${t.message}", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    if (!response.isSuccessful) {
                        Toast.makeText(context, "code ${response.code()}", Toast.LENGTH_LONG).show()
                        return
                    }

                    if (response.body() != null && response.body()?.result!!) {
                        resetViews()
                        Toast.makeText(context, "Patient successfully added", Toast.LENGTH_LONG).show()
                    } else {
                        errorTextView?.text = getString(R.string.cannot_add_pat)
                    }
                }
            })
    }

    override fun onClick(v: View?) {
        val lastName = lastNameView?.text.toString()
        val firstName = firstNameView?.text.toString()
        val fatherName = fatherNameView?.text.toString()
        val diagnosisName = diagnosisNameView?.text.toString()
        val wardName = wardNameView?.text.toString()
        var errorText: String? = null
        when (PatientHelper.checkCorrectPatientData(lastName, firstName, fatherName, diagnosisName, wardName)) {
            PatientDataCorrectType.INCORRECT_LAST_NAME -> errorText = "Incorrect last name, try again"
            PatientDataCorrectType.INCORRECT_FIRST_NAME -> errorText = "Incorrect first name, try again"
            PatientDataCorrectType.INCORRECT_FATHER_NAME -> errorText = "Incorrect father name, try again"
            PatientDataCorrectType.INCORRECT_DIAGNOSIS_NAME -> errorText = "Incorrect diagnosis name, try again"
            PatientDataCorrectType.INCORRECT_WARD_NAME -> errorText = "Incorrect ward name, try again"
            PatientDataCorrectType.CORRECT -> {
                errorTextView?.text = ""
                doRequestAddPatient(this, People(-1, firstName, lastName, fatherName,
                    Diagnosis(-1, diagnosisName),
                    Ward(-1, wardName, -1, Collections.emptyList())))
                return
            }
        }
        errorTextView?.text = errorText
    }

    private fun resetViews() {
        lastNameView?.text?.clear()
        firstNameView?.text?.clear()
        fatherNameView?.text?.clear()
        diagnosisNameView?.text?.clear()
        wardNameView?.text?.clear()
        lastNameView?.requestFocus()
    }
}
