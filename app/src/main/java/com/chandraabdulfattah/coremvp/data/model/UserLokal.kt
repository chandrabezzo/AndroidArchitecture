package com.chandraabdulfattah.coremvp.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.chandraabdulfattah.coremvp.util.constanta.AppConstans

/**
 * Created by bezzo on 11/01/18.
 * Must have one primary key with non null values
 * Add attribute as primary key using @PrimaryKey
 * If primary key value as Auto Increment add attribute autoGenerate = true
 * Add attribute non null values using @NonNull
 */

@Entity(tableName = AppConstans.USER)
class UserLokal {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id : Long = 0

    @ColumnInfo(name = "nama")
    var nama : String? = null

    @ColumnInfo(name = "jabatan")
    var jabatan : String? = null
}
