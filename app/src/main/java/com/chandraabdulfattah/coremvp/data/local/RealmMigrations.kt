package com.chandraabdulfattah.coremvp.data.local

import io.realm.DynamicRealm
import io.realm.RealmMigration

class RealmMigrations : RealmMigration {

    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
//        var schema = realm?.schema
//
//        if (oldVersion == 1L){
//            var userLocalSchema = schema?.localGetByKey(UserLokal.NAME)
//            userLocalSchema.addField("age", Int::class.java)
//        }
    }
}