package com.example.polyclinic.polyclinic.android.polyclinic

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.polyclinic.polyclinic.android.BaseToolbarActivity
import com.example.polyclinic.polyclinic.android.R
import com.example.polyclinic.polyclinic.android.polyclinic.patient.add.AddPatientActivity
import com.example.polyclinic.polyclinic.android.polyclinic.patient.all.AllPatientActivity
import com.example.polyclinic.polyclinic.android.polyclinic.wards.all.AllWardsActivity

class PolyclinicMainToolbarActivity : BaseToolbarActivity() {

    companion object {
        const val USERNAME_KEY = "username_key"
    }

    private var welcomeUserTextView: TextView? = null
    private var allPersonTextView: TextView? = null
    private var addPersonTextView: TextView? = null
    private var allWardsTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        welcomeUserTextView = findViewById(R.id.user_welcome_tv)
        welcomeUserTextView!!.text = String.format(
            getString(R.string.welcome_text), intent.getStringExtra(USERNAME_KEY)
        )

        allPersonTextView = findViewById(R.id.all_person)
        addPersonTextView = findViewById(R.id.add_person)
        allWardsTextView = findViewById(R.id.all_wards)
        allPersonTextView!!.setOnClickListener { handleAllPersonClick() }
        addPersonTextView!!.setOnClickListener { handleAddPersonClick() }
        allWardsTextView!!.setOnClickListener { handleAllWardsClick() }

        setToolbarTitle(R.string.polyclinic_title)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_polyclinic_main_toolbar
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.polyclinic_action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.all_person_item -> {
                handleAllPersonClick()
                return true
            }
            R.id.add_person_item -> {
                handleAddPersonClick()
                return true
            }
            R.id.all_wards_item -> {
                handleAllWardsClick()
                return true
            }
            else -> false
        }
    }

    private fun handleAllPersonClick() {
        startActivity(Intent(this, AllPatientActivity::class.java))
    }

    private fun handleAddPersonClick() {
        startActivity(Intent(this, AddPatientActivity::class.java))
    }

    private fun handleAllWardsClick() {
        startActivity(Intent(this, AllWardsActivity::class.java))
    }
}
