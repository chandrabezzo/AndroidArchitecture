package com.chandraabdulfattah.coremvp.data.network

import com.androidnetworking.common.Priority
import com.chandraabdulfattah.coremvp.util.AppLogger
import com.rx2androidnetworking.Rx2ANRequest
import com.rx2androidnetworking.Rx2AndroidNetworking
import okhttp3.OkHttpClient
import org.json.JSONObject
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bezzo on 11/01/18.
 */

@Singleton
class ApiHelper @Inject
constructor() : ApiHelperContract {

    var okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

    var httpClientUpload = OkHttpClient().newBuilder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build()

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
        getRequest.setOkHttpClient(okHttpClient)

        return getRequest.build()
    }

    override fun post(endpoint: String, params : Map<String, String>?, paths: Map<String, String>?,
                      headers: Map<String, String>?, body: Any?): Rx2ANRequest {

        val postRequest = Rx2AndroidNetworking.post(endpoint)
        AppLogger.i("endpoint : $endpoint")

        if (params != null){
            postRequest.addQueryParameter(params)
            AppLogger.i("params : ${params.toString()}")
        }

        if (paths != null) {
            postRequest.addPathParameter(paths)
            AppLogger.i("paths : " + paths.toString())
        }

        if (headers != null) {
            postRequest.addHeaders(headers)
            AppLogger.i("headers : ${headers.toString()}")
        }

        postRequest.addApplicationJsonBody(body)
        postRequest.setPriority(Priority.MEDIUM)
        postRequest.setOkHttpClient(okHttpClient)

        return postRequest.build()
    }

    override fun put(endpoint: String, params : Map<String, String>?, paths: Map<String, String>?,
                     headers: Map<String, String>?, body: Any?): Rx2ANRequest {

        val putRequest = Rx2AndroidNetworking.put(endpoint)

        if (params != null){
            putRequest.addQueryParameter(params)
            AppLogger.i("Params : ${params.toString()}")
        }

        if (headers != null) {
            putRequest.addHeaders(headers)
            AppLogger.i("Headers : ${headers.toString()}")
        }

        if (paths != null) {
            putRequest.addPathParameter(paths)
            AppLogger.i("Paths : ${paths.toString()}")
        }

        putRequest.addBodyParameter(body)
        putRequest.setPriority(Priority.MEDIUM)
        putRequest.setOkHttpClient(okHttpClient)

        return putRequest.build()
    }

    override fun delete(endpoint: String, params: Map<String, String>?, paths: Map<String, String>?,
                        headers: Map<String, String>?, body: Any?): Rx2ANRequest {

        val deleteRequest = Rx2AndroidNetworking.delete(endpoint)

        if (params != null){
            deleteRequest.addQueryParameter(params)
            AppLogger.i("Params : ${params.toString()}")
        }

        if (headers != null) {
            deleteRequest.addHeaders(headers)
            AppLogger.i("Headers : ${headers.toString()}")
        }

        if (paths != null) {
            deleteRequest.addPathParameter(paths)
            AppLogger.i("Paths : ${paths.toString()}")
        }

        deleteRequest.addBodyParameter(body)
        deleteRequest.setPriority(Priority.MEDIUM)
        deleteRequest.setOkHttpClient(okHttpClient)

        return deleteRequest.build()
    }

    override fun download(endpoint: String, savedLocation: String, fileName: String,
                          params: Map<String, String>?, paths: Map<String, String>?,
                          headers: Map<String, String>?): Rx2ANRequest {

        val downloadBuilder = Rx2AndroidNetworking.download(endpoint,
                savedLocation, fileName)
        AppLogger.i("endpoint : " + endpoint)

        if (headers != null) {
            downloadBuilder.addHeaders(headers)
            AppLogger.i("endpoint : " + headers.toString())
        }

        if (params != null) {
            downloadBuilder.addQueryParameter(params)
            AppLogger.i("params : " + params.toString())
        }

        if (paths != null) {
            downloadBuilder.addPathParameter(paths)
            AppLogger.i("Path : " + paths.toString())
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
                .setOkHttpClient(httpClientUpload)
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
                .setOkHttpClient(httpClientUpload)
                .build()
    }
}
