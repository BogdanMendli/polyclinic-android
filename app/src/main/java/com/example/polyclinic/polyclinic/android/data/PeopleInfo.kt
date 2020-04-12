package com.example.polyclinic.polyclinic.android.data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class PeopleInfo : Parcelable, Serializable {

    val firstName: String?
    val lastName: String?
    val fatherName: String?
    val diagnosis: DiagnosisInfo?
    val ward: WardInfo?

    constructor(parcel: Parcel) {
        this.lastName = parcel.readString()
        this.firstName = parcel.readString()
        this.fatherName = parcel.readString()
        this.diagnosis = parcel.readParcelable(Diagnosis::class.java.classLoader)
        this.ward = parcel.readParcelable(Ward::class.java.classLoader)
    }

    constructor(
        firstName: String,
        lastName: String,
        fatherName: String,
        diagnosis: DiagnosisInfo,
        ward: WardInfo
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.fatherName = fatherName
        this.ward = ward
        this.diagnosis = diagnosis
    }

    constructor(people: People) : this(
        people.firstName,
        people.lastName,
        people.fatherName,
        DiagnosisInfo(people.diagnosis),
        WardInfo(people.ward)
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeString(lastName)
            it.writeString(firstName)
            it.writeString(fatherName)
            it.writeParcelable(diagnosis, flags)
            it.writeParcelable(ward, flags)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PeopleInfo> {
        override fun createFromParcel(parcel: Parcel): PeopleInfo {
            return PeopleInfo(
                parcel
            )
        }

        override fun newArray(size: Int): Array<PeopleInfo?> {
            return arrayOfNulls(size)
        }
    }
}