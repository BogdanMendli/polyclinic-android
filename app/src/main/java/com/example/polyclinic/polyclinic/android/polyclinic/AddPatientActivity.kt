package com.example.polyclinic.polyclinic.android.polyclinic

import android.os.Bundle
import com.example.polyclinic.polyclinic.android.HomeButtonToolbarActivity
import com.example.polyclinic.polyclinic.android.R

class AddPatientActivity : HomeButtonToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_patient
    }
}
