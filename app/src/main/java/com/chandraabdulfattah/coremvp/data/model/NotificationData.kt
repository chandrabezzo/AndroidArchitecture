package com.chandraabdulfattah.coremvp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by bezzo on 09/01/18.
 */
class NotificationData {
    @SerializedName("attribut")
    @Expose
    var attribut: String? = null
}