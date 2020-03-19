package com.example.polyclinic.polyclinic.android.polyclinic.patient.add

import androidx.annotation.IntDef

@IntDef(
    PatientDataCorrectType.INCORRECT_LAST_NAME,
    PatientDataCorrectType.INCORRECT_FIRST_NAME,
    PatientDataCorrectType.INCORRECT_FATHER_NAME,
    PatientDataCorrectType.INCORRECT_DIAGNOSIS_NAME,
    PatientDataCorrectType.INCORRECT_WARD_NAME,
    PatientDataCorrectType.CORRECT
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class PatientDataCorrectType {
    companion object {
        const val INCORRECT_LAST_NAME = 0
        const val INCORRECT_FIRST_NAME = 1
        const val INCORRECT_FATHER_NAME = 2
        const val INCORRECT_DIAGNOSIS_NAME = 3
        const val INCORRECT_WARD_NAME = 4
        const val CORRECT = 5
    }
}