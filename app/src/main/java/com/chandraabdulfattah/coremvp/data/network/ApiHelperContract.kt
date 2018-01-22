package com.chandraabdulfattah.coremvp.data.network

import com.google.gson.JsonObject
import com.rx2androidnetworking.Rx2ANRequest
import org.json.JSONObject
import java.io.File

/**
 * Created by bezzo on 11/01/18.
 */
interface ApiHelperContract {

    fun get(endpoint: String, params: Map<String, String>?,
                              paths: Map<String, String>?, headers: Map<String, String>?): Rx2ANRequest

    fun post(endpoint: String, headers: Map<String, String>?,
                      paths: Map<String, String>?, body: Map<String, String>?): Rx2ANRequest

    fun post(endpoint: String, headers: Map<String, String>?,
                      paths: Map<String, String>?, body: Any): Rx2ANRequest

    fun postJson(endpoint: String, headers: Map<String, String>?,
                          paths: Map<String, String>?, body: JSONObject): Rx2ANRequest

    fun postJsonObject(endpoint: String, headers: Map<String, String>?,
                                paths: Map<String, String>?, jsonObject: Any): Rx2ANRequest

    fun postFile(endpoint: String, headers: Map<String, String>?,
                          paths: Map<String, String>?, file: File): Rx2ANRequest

    fun put(endpoint: String, headers: Map<String, String>?,
                     paths: Map<String, String>?, body: Map<String, String>?): Rx2ANRequest

    fun put(endpoint: String, headers: Map<String, String>?,
                     paths: Map<String, String>?, body: Any): Rx2ANRequest

    fun putJson(endpoint: String, headers: Map<String, String>?,
                         paths: Map<String, String>?, body: JsonObject): Rx2ANRequest

    fun putFile(endpoint: String, headers: Map<String, String>?,
                         paths: Map<String, String>?, file: File): Rx2ANRequest

    fun putJsonObject(endpoint: String, headers: Map<String, String>?,
                               paths: Map<String, String>?, jsonObject: Any): Rx2ANRequest

    fun delete(endpoint: String, headers: Map<String, String>?,
                        paths: Map<String, String>?): Rx2ANRequest

    fun delete(endpoint: String, headers: Map<String, String>?,
                        paths: Map<String, String>?, body: Map<String, String>?): Rx2ANRequest

    fun delete(endpoint: String, headers: Map<String, String>?,
                        paths: Map<String, String>?, body: Any): Rx2ANRequest

    fun deleteJson(endpoint: String, headers: Map<String, String>?,
                            paths: Map<String, String>?, body: JSONObject): Rx2ANRequest

    fun deleteJsonObject(endpoint: String, headers: Map<String, String>?,
                                  paths: Map<String, String>?, jsonObject: Any): Rx2ANRequest

    fun download(endpoint: String, savedLocation: String, fileName: String,
                          params: Map<String, String>?, paths: Map<String, String>?,
                          headers: Map<String, String>?): Rx2ANRequest

    fun upload(endpoint: String, params: Map<String, String>?, paths: Map<String, String>?,
                        headers: Map<String, String>?, parameterFile: String, file: File,
                        multipart: Map<String, String>?): Rx2ANRequest

    fun uploads(endpoint: String, params: Map<String, String>, paths: Map<String, String>,
                headers: Map<String, String>, files: Map<String, File>,
                multipart: Map<String, String>): Rx2ANRequest
}