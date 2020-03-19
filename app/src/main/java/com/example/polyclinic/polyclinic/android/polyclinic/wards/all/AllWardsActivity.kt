package com.example.polyclinic.polyclinic.android.polyclinic.wards.all

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.polyclinic.polyclinic.android.HomeButtonToolbarActivity
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.api.PolyclinicApiService
import com.example.polyclinic.polyclinic.android.api.RetrofitFactory
import com.example.polyclinic.polyclinic.android.data.Ward
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllWardsActivity : HomeButtonToolbarActivity() {

    private var adapter: AllWardsAdapter? = null
    private var patientRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle(R.string.all_wards_all_wards_text)
        adapter = AllWardsAdapter()
        patientRecyclerView = findViewById(R.id.ward_recycler)
        patientRecyclerView!!.adapter = adapter
        patientRecyclerView!!.layoutManager = LinearLayoutManager(this)
        doRequestPatient(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_all_wards
    }

    private fun doRequestPatient(context: Context) {
        RetrofitFactory.create(PolyclinicApiService::class.java).getWards().enqueue(object: Callback<List<Ward>> {
            override fun onFailure(call: Call<List<Ward>>, t: Throwable) {
                Toast.makeText(context, "something went wrong ${t.message}", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Ward>>, response: Response<List<Ward>>) {
                if (!response.isSuccessful) {
                    Toast.makeText(context, "code ${response.code()}", Toast.LENGTH_LONG).show()
                    return
                }

                adapter!!.setWards(response.body())
            }
        })
    }
}
