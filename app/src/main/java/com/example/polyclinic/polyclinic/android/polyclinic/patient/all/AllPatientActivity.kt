package com.example.polyclinic.polyclinic.android.polyclinic.patient.all

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.polyclinic.polyclinic.android.HomeButtonToolbarActivity
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.api.PolyclinicApiService
import com.example.polyclinic.polyclinic.android.api.RetrofitFactory
import com.example.polyclinic.polyclinic.android.data.People
import com.example.polyclinic.polyclinic.android.data.PeopleInfo
import com.example.polyclinic.polyclinic.android.polyclinic.patient.add.AddEditPatientActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllPatientActivity : HomeButtonToolbarActivity(),
    AllPatientViewHolder.OnEditPersonClickListener {

    companion object {
        const val PERSON_FIO = "person_fio_key"
        const val PERSON_ID = "person_id_key"
        const val PERSON_INFO = "person_info_last_name_key"
        const val PERSON_WARD = "person_ward_key"
        const val PERSON_DIAGNOSIS = "person_diagnosis_key"
        const val PERSON_ADAPTER_POSITION = "person_adapter_position_key"
        const val EDIT_PERSON_REQUEST_CODE = 956
    }

    private var adapter: AllPatientAdapter? = null
    private var patientRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle(R.string.all_person_all_patients_text)
        adapter = AllPatientAdapter(this)
        patientRecyclerView = findViewById(R.id.patient_recycler)
        patientRecyclerView?.adapter = adapter
        patientRecyclerView?.layoutManager = LinearLayoutManager(this)
        doRequestPatient(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_PERSON_REQUEST_CODE) {
            doRequestPatient(this)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_all_patient
    }

    private fun doRequestPatient(context: Context) {
        RetrofitFactory.create(PolyclinicApiService::class.java).getUsers()
            .enqueue(object : Callback<List<People>> {
                override fun onFailure(call: Call<List<People>>, t: Throwable) {
                    Toast.makeText(context, "something went wrong ${t.message}", Toast.LENGTH_LONG)
                        .show()
                }

                override fun onResponse(
                    call: Call<List<People>>,
                    response: Response<List<People>>
                ) {
                    if (!response.isSuccessful) {
                        Toast.makeText(context, "code ${response.code()}", Toast.LENGTH_LONG).show()
                        return
                    }

                    adapter?.setPatients(response.body())
                }
            })
    }

    override fun onEditPersonClick(
        id: Int,
        fio: String,
        diagnosis: String,
        ward: String,
        position: Int
    ) {
        val intent = Intent(this, AddEditPatientActivity::class.java)
        intent.putExtra(PERSON_ID, id)
        intent.putExtra(PERSON_FIO, fio)
        intent.putExtra(PERSON_DIAGNOSIS, diagnosis)
        intent.putExtra(PERSON_WARD, ward)
        intent.putExtra(PERSON_ADAPTER_POSITION, position)
        intent.putExtra(AddEditPatientActivity.PERSON_MODE, false)
        startActivityForResult(intent, EDIT_PERSON_REQUEST_CODE)
    }
}
