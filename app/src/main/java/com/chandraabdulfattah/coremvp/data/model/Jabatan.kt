package com.chandraabdulfattah.coremvp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Jabatan : RealmObject() {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("nama")
    @Expose
    var nama: String? = null
}