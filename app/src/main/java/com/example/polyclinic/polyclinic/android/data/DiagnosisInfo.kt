package com.example.polyclinic.polyclinic.android.data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class DiagnosisInfo : Serializable, Parcelable {

    val name: String?

    constructor(parcel: Parcel) {
        this.name = parcel.readString()
    }

    constructor(name: String) {
        this.name = name
    }

    constructor(diagnosis: Diagnosis) : this(diagnosis.name)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DiagnosisInfo> {
        override fun createFromParcel(parcel: Parcel): DiagnosisInfo {
            return DiagnosisInfo(parcel)
        }

        override fun newArray(size: Int): Array<DiagnosisInfo?> {
            return arrayOfNulls(size)
        }
    }
}