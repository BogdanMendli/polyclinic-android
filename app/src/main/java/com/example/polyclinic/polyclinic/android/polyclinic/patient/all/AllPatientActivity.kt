package com.example.polyclinic.polyclinic.android.polyclinic.patient.all

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.polyclinic.polyclinic.android.HomeButtonToolbarActivity
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.api.PolyclinicApiService
import com.example.polyclinic.polyclinic.android.api.RetrofitFactory
import com.example.polyclinic.polyclinic.android.data.People
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllPatientActivity : HomeButtonToolbarActivity() {

    private var adapter: AllPatientAdapter? = null
    private var patientRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle(R.string.all_person_all_patients_text)
        adapter = AllPatientAdapter()
        patientRecyclerView = findViewById(R.id.patient_recycler)
        patientRecyclerView!!.adapter = adapter
        patientRecyclerView!!.layoutManager = LinearLayoutManager(this)
        doRequestPatient(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_all_patient
    }

    private fun doRequestPatient(context: Context) {
        RetrofitFactory.create(PolyclinicApiService::class.java).getUsers().enqueue(object: Callback<List<People>>{
            override fun onFailure(call: Call<List<People>>, t: Throwable) {
                Toast.makeText(context, "something went wrong ${t.message}", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<People>>, response: Response<List<People>>) {
                if (!response.isSuccessful) {
                    Toast.makeText(context, "code ${response.code()}", Toast.LENGTH_LONG).show()
                    return
                }

                adapter!!.setPatients(response.body())
            }
        })
    }
}
