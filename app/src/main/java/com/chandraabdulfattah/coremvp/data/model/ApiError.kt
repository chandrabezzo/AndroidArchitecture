package com.chandraabdulfattah.coremvp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by janisharali on 28/01/17.
 */

class ApiError {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("errors")
    @Expose
    var errors: List<Error>? = null
    @SerializedName("statusCode")
    @Expose
    var statusCode: String? = null
}
