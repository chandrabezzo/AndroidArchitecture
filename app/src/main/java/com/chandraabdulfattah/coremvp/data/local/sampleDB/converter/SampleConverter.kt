package com.chandraabdulfattah.coremvp.data.local.sampleDB.converter

import android.arch.persistence.room.TypeConverter
import com.chandraabdulfattah.coremvp.data.model.UserLokal
import com.google.gson.Gson

class SampleConverter {

    @TypeConverter
    fun jsonToModel(json : String) : UserLokal {
        return Gson().fromJson<UserLokal>(json, UserLokal::class.java)
    }

    @TypeConverter
    fun modelToJson(value : UserLokal) : String {
        return Gson().toJson(value)
    }
}