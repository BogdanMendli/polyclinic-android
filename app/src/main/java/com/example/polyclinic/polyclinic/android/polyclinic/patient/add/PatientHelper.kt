package com.example.polyclinic.polyclinic.android.polyclinic.patient.add

import com.example.polyclinic.polyclinic.android.data.People

object PatientHelper {

    @JvmStatic
    @PatientDataCorrectType
    fun checkCorrectPatientData(
        lastName: String,
        firstName: String,
        fatherName: String,
        diagnosisName: String,
        wardName: String
    ): Int {
        if (!checkCorrectPeopleName(lastName)) {
            return PatientDataCorrectType.INCORRECT_LAST_NAME
        }

        if (!checkCorrectPeopleName(firstName)) {
            return PatientDataCorrectType.INCORRECT_FIRST_NAME
        }

        if (!checkCorrectPeopleName(fatherName)) {
            return PatientDataCorrectType.INCORRECT_FATHER_NAME
        }

        if (!checkCorrectOtherName(diagnosisName)) {
            return PatientDataCorrectType.INCORRECT_DIAGNOSIS_NAME
        }

        if (!checkCorrectOtherName(wardName)) {
            return PatientDataCorrectType.INCORRECT_WARD_NAME
        }

        return PatientDataCorrectType.CORRECT
    }

    private fun checkCorrectPeopleName(name: String): Boolean =
        name.matches(Regex("[a-bA-z[-./_,]]{2,20}"))

    private fun checkCorrectOtherName(name: String): Boolean =
        name.matches(Regex("[a-bA-z0-9]{2,20}"))
}