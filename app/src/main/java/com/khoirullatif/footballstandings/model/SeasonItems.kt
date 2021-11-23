package com.khoirullatif.footballstandings.model

import android.os.Parcel
import android.os.Parcelable

class SeasonItems() : Parcelable{
    var year: String? = null
    var displayName: String? = null

    constructor(parcel: Parcel) : this() {
        year = parcel.readString()
        displayName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(year)
        parcel.writeString(displayName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SeasonItems> {
        override fun createFromParcel(parcel: Parcel): SeasonItems {
            return SeasonItems(parcel)
        }

        override fun newArray(size: Int): Array<SeasonItems?> {
            return arrayOfNulls(size)
        }
    }
}