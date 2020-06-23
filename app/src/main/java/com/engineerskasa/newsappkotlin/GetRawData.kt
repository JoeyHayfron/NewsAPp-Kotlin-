package com.engineerskasa.newsappkotlin

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus {
    OK, NOT_INITIALIZED, ERROR, FAILED_OR_EMPTY, PERMISSION_ERROR, IDLE
}

class GetRawData(private val listener: OnDownloadComplete) : AsyncTask<String, Void, String>() {
    private var status = DownloadStatus.IDLE
    private val TAG = "GetRawData"

    interface OnDownloadComplete {
        fun onDownloadComplete(status: DownloadStatus, data: String)
        fun onError(status: DownloadStatus, data: String)
    }

    override fun onPostExecute(result: String) {
        Log.d(TAG, ".onPostExecute: Download Complete with $result")
        listener.onDownloadComplete(status, result)
    }

    override fun doInBackground(vararg params: String?): String {
        if (params[0] == null) {
            status = DownloadStatus.NOT_INITIALIZED
            return "URL not specified"
        }
        try {
            status = DownloadStatus.OK
            return URL(params[0]).readText()
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is MalformedURLException -> {
                    status = DownloadStatus.NOT_INITIALIZED
                    "doInBackground Download Failed with a bad url error ${e.message}"
                }
                is IOException -> {
                    status = DownloadStatus.FAILED_OR_EMPTY
                    "doInBackground Download Failed with IO Exception ${e.message}"
                }
                is SecurityException -> {
                    status = DownloadStatus.PERMISSION_ERROR
                    "doInBackground Download Failed with Security Exception ${e.message}"
                }
                else -> {
                    status = DownloadStatus.ERROR
                    "doInBackground Download Failed with Unknown Error ${e.message}"
                }
            }
            Log.d(TAG, ".doInBackground: Error $errorMessage")
            listener.onError(status, errorMessage)
            return errorMessage
        }
    }
}
