package com.chandraabdulfattah.coremvp.data.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by bezzo on 14/11/17.
 */

class User {
    @SerializedName("nama")
    @Expose
    var nama: String? = null
    @SerializedName("jabatan")
    @Expose
    var jabatan: String? = null
}
