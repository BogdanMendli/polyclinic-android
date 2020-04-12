package com.example.polyclinic.polyclinic.android.data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class WardInfo : Serializable, Parcelable {

    val name: String?
    val maxCount: Int

    constructor(parcel: Parcel) {
        this.name = parcel.readString()
        this.maxCount = parcel.readInt()
    }

    constructor(name: String, maxCount: Int) {
        this.name = name
        this.maxCount = maxCount
    }

    constructor(ward: Ward) : this(ward.name, ward.maxCount)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(maxCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WardInfo> {
        override fun createFromParcel(parcel: Parcel): WardInfo {
            return WardInfo(parcel)
        }

        override fun newArray(size: Int): Array<WardInfo?> {
            return arrayOfNulls(size)
        }
    }
}