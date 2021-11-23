package com.khoirullatif.footballstandings.model

import android.os.Parcel
import android.os.Parcelable

class LeagueItems() : Parcelable {
    var id: String? = null
    var logo: String? = null
    var name: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        logo = parcel.readString()
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(logo)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LeagueItems> {
        override fun createFromParcel(parcel: Parcel): LeagueItems {
            return LeagueItems(parcel)
        }

        override fun newArray(size: Int): Array<LeagueItems?> {
            return arrayOfNulls(size)
        }
    }
}