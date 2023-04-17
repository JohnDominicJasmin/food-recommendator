package com.example.foodrecommendation.api

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.example.foodrecommendation.model.logmeal.LogMealApiDto
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*

import java.io.File


import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okio.IOException
import org.json.JSONObject

import timber.log.Timber
import java.io.FileOutputStream
import java.io.InputStream

const val API_KEY = "dc91f4ccf87aa9ee9fdac66fcc89b98dbbda920b"

class CalorieMamaManager {

    private fun getRealPathFromURI(uri: Uri, context: Context): String? {
        val returnCursor = context.contentResolver.query(uri, null, null, null, null)
        val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)

        val file = File(context.filesDir, name)
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            var read = 0
            val maxBufferSize = 1 * 1024 * 1024
            val bytesAvailable: Int = inputStream?.available() ?: 0
            //int bufferSize = 1024;
            val bufferSize = Math.min(bytesAvailable, maxBufferSize)
            val buffers = ByteArray(bufferSize)
            while (inputStream?.read(buffers).also {
                    if (it != null) {
                        read = it
                    }
                } != -1) {
                outputStream.write(buffers, 0, read)
            }
            Timber.e("File Size", "Size " + file.length())
            inputStream?.close()
            outputStream.close()
            Timber.e("File Path", "Path " + file.path)

        } catch (e: java.lang.Exception) {
            Timber.e("Exception", e.message!!)
        }
        return file.path
    }

    fun getResults(uri :Uri, context: Context, logMealCallback: LogMealCallback) {

        GlobalScope.launch(Dispatchers.IO) {
            val imagePath = getRealPathFromURI(uri, context)
            val client = OkHttpClient()

            val imgFile = File(imagePath)
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "image", imgFile.name,
                    imgFile.asRequestBody("image/*".toMediaTypeOrNull()))
                .build()

            val request = Request.Builder()
                .url("https://api.logmeal.es/v2/image/segmentation/complete")
                .addHeader("Authorization", "Bearer dc91f4ccf87aa9ee9fdac66fcc89b98dbbda920b")
                .post(requestBody)
                .build()

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                    logMealCallback.onFailure("Failed to detect dishes")
                }

                override fun onResponse(call: Call, response: Response) {

                    val responseBody = response.body?.string()

                    val jsonObject = JSONObject(responseBody)
                    val imageId = jsonObject.optString("imageId")

                    val code = jsonObject.optInt("code")

                    if (code == 114) {
                        logMealCallback.onFailure("You have reached the request limit within the last 24h. Try again later.")
                        return
                    }


                    if (imageId.isNotEmpty()) {
                        val requestBody = RequestBody.create(
                            "application/json".toMediaTypeOrNull(),
                            "{\"imageId\": \"$imageId\"}")
                        val request = Request.Builder()
                            .url("https://api.logmeal.es/v2/recipe/ingredients")
                            .addHeader(
                                "Authorization",
                                "Bearer ${API_KEY}")
                            .post(requestBody)
                            .build()

                        client.newCall(request).enqueue(object : Callback {
                            override fun onResponse(call: Call, response: Response) {
                                val responseBody = response.body?.string()
                                val gson = Gson()
                                val logMealApiDto =
                                    gson.fromJson(responseBody, LogMealApiDto::class.java)
                                logMealCallback.onResponse(logMealApiDto)

                            }

                            override fun onFailure(call: Call, e: IOException) {
                                logMealCallback.onFailure("Failed to get ingredients")
                            }
                        })
                    } else {
                        logMealCallback.onFailure("Failed to get imageId")
                    }
                }
            })
        }

        }
}

interface LogMealCallback{
    fun onResponse(jsonObject: LogMealApiDto)
    fun onFailure(message:String)
}

