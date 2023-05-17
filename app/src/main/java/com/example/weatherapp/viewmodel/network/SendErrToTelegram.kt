package com.example.weatherapp.viewmodel.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException


    fun sendMessageToTG(message: String) {
        val botToken = "6211255792:AAHLQAGYhaXo_pgvGMoAKPLQkW7GzdN_ZWw"
        val chatId = 278008469

        val url = "https://api.telegram.org/bot$botToken/sendMessage"
        val json = "{\"chat_id\":$chatId,\"text\":\"$message\"}"
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.close()
            }
        })
    }