package com.chandraabdulfattah.coremvp.data.network

import com.androidnetworking.common.Priority
import com.chandraabdulfattah.coremvp.util.AppLogger
import com.google.gson.JsonObject
import com.rx2androidnetworking.Rx2ANRequest
import com.rx2androidnetworking.Rx2AndroidNetworking

import org.json.JSONObject

import java.io.File
import java.util.concurrent.Executors

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bezzo on 11/01/18.
 */

@Singleton
class ApiHelper @Inject
constructor() : ApiHelperContract {

    override fun get(endpoint: String, params: Map<String, String>?,
                              paths: Map<String, String>?, headers: Map<String, String>?): Rx2ANRequest {

        val getRequest = Rx2AndroidNetworking.get(endpoint)

        AppLogger.i("endpoint : " + endpoint)

        if (headers != null) {
            getRequest.addHeaders(headers)
            AppLogger.i("headers : " + headers.toString())
        }

        if (params != null) {
            getRequest.addQueryParameter(params)
            AppLogger.i("params : " + params.toString())
        }

        if (paths != null) {
            getRequest.addPathParameter(paths)
            AppLogger.i("paths : " + paths.toString())
        }

        getRequest.setPriority(Priority.LOW)

        return getRequest.build()
    }

    override fun post(endpoint: String, headers: Map<String, String>?,
                      paths: Map<String, String>?, body: Map<String, String>?): Rx2ANRequest {

        val postRequest = Rx2AndroidNetworking.post(endpoint)

        if (headers != null) {
            postRequest.addHeaders(headers)
        }

        if (paths != null) {
            postRequest.addPathParameter(paths)
        }

        postRequest.addBodyParameter(body)

        postRequest.setPriority(Priority.MEDIUM)

        return postRequest.build()
    }

    override fun post(endpoint: String, headers: Map<String, String>?,
                      paths: Map<String, String>?, body: Any): Rx2ANRequest {
        val postRequest = Rx2AndroidNetworking.post(endpoint)

        if (headers != null) {
            postRequest.addHeaders(headers)
        }

        if (paths != null) {
            postRequest.addPathParameter(paths)
        }

        postRequest.addBodyParameter(body)
        postRequest.setPriority(Priority.MEDIUM)

        return postRequest.build()
    }

    override fun postJson(endpoint: String, headers: Map<String, String>?,
                          paths: Map<String, String>?, body: JSONObject): Rx2ANRequest {

        val postRequest = Rx2AndroidNetworking.post(endpoint)

        if (headers != null) {
            postRequest.addHeaders(headers)
        }

        if (paths != null) {
            postRequest.addPathParameter(paths)
        }

        postRequest.addJSONObjectBody(body)
        postRequest.setPriority(Priority.MEDIUM)

        return postRequest.build()
    }

    override fun postJsonObject(endpoint: String, headers: Map<String, String>?,
                                paths: Map<String, String>?, jsonObject: Any): Rx2ANRequest {

        val postRequest = Rx2AndroidNetworking.post(endpoint)
        AppLogger.i("endpoint : " + endpoint)

        if (headers != null) {
            postRequest.addHeaders(headers)
            AppLogger.i("headers : " + headers.toString())
        }

        if (paths != null) {
            postRequest.addPathParameter(paths)
            AppLogger.i("paths : " + paths.toString())
        }

        postRequest.addApplicationJsonBody(jsonObject)
        AppLogger.i("body : " + jsonObject.toString())
        postRequest.setPriority(Priority.MEDIUM)

        return postRequest.build()
    }

    override fun postFile(endpoint: String, headers: Map<String, String>?,
                          paths: Map<String, String>?, file: File): Rx2ANRequest {

        val postRequest = Rx2AndroidNetworking.post(endpoint)

        if (headers != null) {
            postRequest.addHeaders(headers)
        }

        if (paths != null) {
            postRequest.addPathParameter(paths)
        }

        postRequest.addFileBody(file)
        postRequest.setPriority(Priority.HIGH)

        return postRequest.build()
    }

    override fun put(endpoint: String, headers: Map<String, String>?,
                     paths: Map<String, String>?, body: Map<String, String>?): Rx2ANRequest {

        val putRequest = Rx2AndroidNetworking.put(endpoint)

        if (headers != null) {
            putRequest.addHeaders(headers)
        }

        if (paths != null) {
            putRequest.addPathParameter(paths)
        }

        putRequest.addBodyParameter(body)
        putRequest.setPriority(Priority.MEDIUM)

        return putRequest.build()
    }

    override fun put(endpoint: String, headers: Map<String, String>?,
                     paths: Map<String, String>?, body: Any): Rx2ANRequest {

        val putRequest = Rx2AndroidNetworking.put(endpoint)

        if (headers != null) {
            putRequest.addHeaders(headers)
        }

        if (paths != null) {
            putRequest.addPathParameter(paths)
        }

        putRequest.addBodyParameter(body)
        putRequest.setPriority(Priority.MEDIUM)

        return putRequest.build()
    }

    override fun putJson(endpoint: String, headers: Map<String, String>?,
                         paths: Map<String, String>?, body: JsonObject): Rx2ANRequest {

        val putRequest = Rx2AndroidNetworking.put(endpoint)

        if (headers != null) {
            putRequest.addHeaders(headers)
        }

        if (paths != null) {
            putRequest.addPathParameter(paths)
        }

        putRequest.addBodyParameter(body)
        putRequest.setPriority(Priority.MEDIUM)

        return putRequest.build()
    }

    override fun putFile(endpoint: String, headers: Map<String, String>?,
                         paths: Map<String, String>?, file: File): Rx2ANRequest {

        val putRequest = Rx2AndroidNetworking.put(endpoint)

        if (headers != null) {
            putRequest.addHeaders(headers)
        }

        if (paths != null) {
            putRequest.addPathParameter(paths)
        }

        putRequest.addFileBody(file)
        putRequest.setPriority(Priority.HIGH)

        return putRequest.build()
    }

    override fun putJsonObject(endpoint: String, headers: Map<String, String>?,
                               paths: Map<String, String>?, jsonObject: Any): Rx2ANRequest {

        val putRequest = Rx2AndroidNetworking.put(endpoint)

        if (headers != null) {
            putRequest.addHeaders(headers)
        }

        if (paths != null) {
            putRequest.addPathParameter(paths)
        }

        putRequest.addApplicationJsonBody(jsonObject)
        putRequest.setPriority(Priority.MEDIUM)

        return putRequest.build()
    }

    override fun delete(endpoint: String, headers: Map<String, String>?,
                        paths: Map<String, String>?): Rx2ANRequest {

        val deleteRequest = Rx2AndroidNetworking.delete(endpoint)

        if (headers != null) {
            deleteRequest.addHeaders(headers)
        }

        if (paths != null) {
            deleteRequest.addPathParameter(paths)
        }

        deleteRequest.setPriority(Priority.MEDIUM)

        return deleteRequest.build()
    }

    override fun delete(endpoint: String, headers: Map<String, String>?,
                        paths: Map<String, String>?, body: Map<String, String>?): Rx2ANRequest {

        val deleteRequest = Rx2AndroidNetworking.delete(endpoint)

        if (headers != null) {
            deleteRequest.addHeaders(headers)
        }

        if (paths != null) {
            deleteRequest.addPathParameter(paths)
        }

        deleteRequest.addBodyParameter(body)
        deleteRequest.setPriority(Priority.MEDIUM)

        return deleteRequest.build()
    }

    override fun delete(endpoint: String, headers: Map<String, String>?,
                        paths: Map<String, String>?, body: Any): Rx2ANRequest {

        val deleteRequest = Rx2AndroidNetworking.delete(endpoint)

        if (headers != null) {
            deleteRequest.addHeaders(headers)
        }

        if (paths != null) {
            deleteRequest.addPathParameter(paths)
        }

        deleteRequest.addBodyParameter(body)
        deleteRequest.setPriority(Priority.MEDIUM)

        return deleteRequest.build()
    }

    override fun deleteJson(endpoint: String, headers: Map<String, String>?,
                            paths: Map<String, String>?, body: JSONObject): Rx2ANRequest {

        val deleteRequest = Rx2AndroidNetworking.delete(endpoint)

        if (headers != null) {
            deleteRequest.addHeaders(headers)
        }

        if (paths != null) {
            deleteRequest.addHeaders(paths)
        }

        deleteRequest.addJSONObjectBody(body)
        deleteRequest.setPriority(Priority.MEDIUM)

        return deleteRequest.build()
    }

    override fun deleteJsonObject(endpoint: String, headers: Map<String, String>?,
                                  paths: Map<String, String>?, jsonObject: Any): Rx2ANRequest {

        val deleteRequest = Rx2AndroidNetworking.delete(endpoint)

        if (headers != null) {
            deleteRequest.addHeaders(headers)
        }

        if (paths != null) {
            deleteRequest.addPathParameter(paths)
        }

        deleteRequest.addApplicationJsonBody(jsonObject)
        deleteRequest.setPriority(Priority.MEDIUM)

        return deleteRequest.build()
    }

    override fun download(endpoint: String, savedLocation: String, fileName: String,
                          params: Map<String, String>?, paths: Map<String, String>?,
                          headers: Map<String, String>?): Rx2ANRequest {

        val downloadBuilder = Rx2AndroidNetworking.download(endpoint,
                savedLocation, fileName)

        if (headers != null) {
            downloadBuilder.addHeaders(headers)
        }

        if (params != null) {
            downloadBuilder.addQueryParameter(params)
        }

        if (paths != null) {
            downloadBuilder.addPathParameter(paths)
        }

        downloadBuilder.setPercentageThresholdForCancelling(50)
        downloadBuilder.setExecutor(Executors.newSingleThreadExecutor())
        downloadBuilder.setPriority(Priority.MEDIUM)

        return downloadBuilder.build()
    }

    override fun upload(endpoint: String, params: Map<String, String>?, paths: Map<String, String>?,
                        headers: Map<String, String>?, parameterFile: String, file: File,
                        multipart: Map<String, String>?): Rx2ANRequest {

        return Rx2AndroidNetworking.upload(endpoint)
                .addHeaders(headers)
                .addMultipartFile(parameterFile, file)
                .addMultipartParameter(multipart)
                .setExecutor(Executors.newSingleThreadExecutor())
                .setPriority(Priority.HIGH)
                .build()
    }

    override fun uploads(endpoint: String, params: Map<String, String>, paths: Map<String, String>,
                headers: Map<String, String>, files: Map<String, File>, multipart: Map<String, String>): Rx2ANRequest {
        return Rx2AndroidNetworking.upload(endpoint)
                .addHeaders(headers)
                .addMultipartFile(files)
                .addMultipartParameter(multipart)
                .setExecutor(Executors.newSingleThreadExecutor())
                .setPriority(Priority.HIGH)
                .build()
    }
}
