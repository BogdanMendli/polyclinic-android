package com.example.polyclinic.polyclinic.android.polyclinic.patient.add

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.polyclinic.polyclinic.android.HomeButtonToolbarActivity
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.api.PolyclinicApiService
import com.example.polyclinic.polyclinic.android.api.RetrofitFactory
import com.example.polyclinic.polyclinic.android.data.*
import com.example.polyclinic.polyclinic.android.polyclinic.patient.all.AllPatientActivity
import kotlinx.android.synthetic.main.activity_add_patient.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.util.*

class AddEditPatientActivity : HomeButtonToolbarActivity(), View.OnClickListener {

    companion object {
        const val PERSON_MODE = "person_mode_add_key"
    }

    private var lastNameView: AutoCompleteTextView? = null
    private var firstNameView: AutoCompleteTextView? = null
    private var fatherNameView: AutoCompleteTextView? = null
    private var diagnosisNameView: AutoCompleteTextView? = null
    private var wardNameView: AutoCompleteTextView? = null
    private var addPatientButton: Button? = null
    private var errorTextView: TextView? = null

    private var isAddMode: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isAddMode = intent.getBooleanExtra(PERSON_MODE, true)
        lastNameView = last_name_input
        firstNameView = first_name_input
        fatherNameView = father_name_input
        diagnosisNameView = diagnosis_name_input
        wardNameView = ward_name_input
        errorTextView = error_tv
        addPatientButton = add_patient_button
        if (!isAddMode) {
            addPatientButton?.text = getString(R.string.save_person_save_text)
            val stringFio = intent.getStringExtra(AllPatientActivity.PERSON_FIO)?.split(" ")

            stringFio?.let {
                lastNameView?.setText(stringFio[0])
                firstNameView?.setText(stringFio[1])
                fatherNameView?.setText(stringFio[2])
            }
            wardNameView?.setText(intent.getStringExtra(AllPatientActivity.PERSON_WARD))
            diagnosisNameView?.setText(intent.getStringExtra(AllPatientActivity.PERSON_DIAGNOSIS))
        }
        addPatientButton?.setOnClickListener(this)
        setToolbarTitle(if (isAddMode) R.string.add_person_add_patient_text else R.string.edit_patient)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_patient
    }

    private fun doRequest(activity: Activity, patient: People) {
        if (isAddMode) {
            RetrofitFactory.create(PolyclinicApiService::class.java).addPatient(patient)
                .enqueue(object :
                    Callback<Result> {
                    override fun onFailure(call: Call<Result>, t: Throwable) {
                        Toast.makeText(
                            activity,
                            "something went wrong ${t.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(call: Call<Result>, response: Response<Result>) {
                        if (!response.isSuccessful) {
                            Toast.makeText(activity, "code ${response.code()}", Toast.LENGTH_LONG)
                                .show()
                        }

                        if (response.body() != null && response.body()?.result!!) {
                            resetViews()
                            Toast.makeText(
                                activity,
                                "Patient successfully added",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        } else if (response.code() == HttpURLConnection.HTTP_CONFLICT) {
                            errorTextView?.text = getString(R.string.ward_full)
                        } else {
                            errorTextView?.text = getString(R.string.cannot_add_pat)
                        }
                    }
                })
        } else {
            RetrofitFactory.create(PolyclinicApiService::class.java).updatePatient(patient)
                .enqueue(object :
                    Callback<Result> {
                    override fun onFailure(call: Call<Result>, t: Throwable) {
                        Toast.makeText(
                            activity,
                            "something went wrong ${t.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(call: Call<Result>, response: Response<Result>) {
                        if (!response.isSuccessful) {
                            Toast.makeText(activity, "code ${response.code()}", Toast.LENGTH_LONG)
                                .show()
                        }

                        if (response.body() != null && response.body()?.result!!) {
                            resetViews()
                            Toast.makeText(
                                activity,
                                "Patient successfully updated",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            intent.putExtra(
                                AllPatientActivity.PERSON_INFO,
                                PeopleInfo(patient) as Parcelable
                            )
                            finish()
                        } else if (response.code() == HttpURLConnection.HTTP_CONFLICT) {
                            errorTextView?.text = getString(R.string.ward_full)
                        } else {
                            errorTextView?.text = getString(R.string.cannot_update_pat)
                        }
                    }
                })
        }
    }

    override fun onClick(v: View?) {
        val lastName = lastNameView?.text.toString()
        val firstName = firstNameView?.text.toString()
        val fatherName = fatherNameView?.text.toString()
        val diagnosisName = diagnosisNameView?.text.toString()
        val wardName = wardNameView?.text.toString()
        var errorText: String? = null
        when (PatientHelper.checkCorrectPatientData(
            lastName,
            firstName,
            fatherName,
            diagnosisName,
            wardName
        )) {
            PatientDataCorrectType.INCORRECT_LAST_NAME -> errorText =
                "Incorrect last name, try again"
            PatientDataCorrectType.INCORRECT_FIRST_NAME -> errorText =
                "Incorrect first name, try again"
            PatientDataCorrectType.INCORRECT_FATHER_NAME -> errorText =
                "Incorrect father name, try again"
            PatientDataCorrectType.INCORRECT_DIAGNOSIS_NAME -> errorText =
                "Incorrect diagnosis name, try again"
            PatientDataCorrectType.INCORRECT_WARD_NAME -> errorText =
                "Incorrect ward name, try again"
            PatientDataCorrectType.CORRECT -> {
                errorTextView?.text = ""
                doRequest(
                    this, People(
                        intent.getIntExtra(AllPatientActivity.PERSON_ID, -1),
                        firstName,
                        lastName,
                        fatherName,
                        Diagnosis(-1, diagnosisName),
                        Ward(-1, wardName, -1, Collections.emptyList())
                    )
                )
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
