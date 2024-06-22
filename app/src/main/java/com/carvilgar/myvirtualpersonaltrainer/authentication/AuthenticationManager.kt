package com.carvilgar.myvirtualpersonaltrainer.authentication

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AuthenticationManager {
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    private val client: OkHttpClient = OkHttpClient()

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveTokenToPreferences(authTokenResponse: AuthTokenResponse, context: Context) {
        val authToken: String = authTokenResponse.accessToken
        val refreshToken: String = authTokenResponse.refreshToken
        val tokenType: String = authTokenResponse.tokenType
        val expiresIn: String = authTokenResponse.expiresIn.toString()

        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().apply {
            putString("authToken", authToken)
            putString("refreshToken", refreshToken)
            putString("tokenType", tokenType)
            putString("expiresIn", expiresIn)
            putString("creationDateTime", formatter.format(authTokenResponse.creationDateTime))
            apply()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTokenFromPreferences(context: Context): Pair<Boolean, AuthTokenResponse?> {
        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val authToken = sharedPreferences.getString("authToken", null)?:""
        val refreshToken = sharedPreferences.getString("refreshToken", null)?:""
        val tokenType = sharedPreferences.getString("tokenType", null)?:""
        val expiresIn = sharedPreferences.getString("authToken", null)?.toLong()?:0
        val creationDateTime = sharedPreferences.getString("creationDateTime", null)?:""

        if (authToken.isNotBlank()) {
            return Pair(true, AuthTokenResponse(authToken, expiresIn, refreshToken, tokenType,
                LocalDateTime.parse(creationDateTime, formatter)))
        }
        return Pair(false, null)
    }

    fun retrieveAutToken(userCredentials: UserCredentials, callback: AuthTokenCallback) {
        val body = MultipartBody.Builder().setType(MultipartBody.FORM)
            .apply {
                addFormDataPart("username", userCredentials.username)
                addFormDataPart("email", userCredentials.email)
                addFormDataPart("password", userCredentials.password)
            }
            .build()

        val authRequest = Request.Builder()
            .url("http://10.0.2.2:5000/token/new?lang=en")
            .post(body)
            .build()

        client.newCall(authRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onError(e.message ?: "Network error")
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string() ?: throw IOException("Empty response body")
                    val gson = Gson()
                    val type = object : TypeToken<AuthTokenResponse>() {}.type
                    val authTokenResponse: AuthTokenResponse = gson.fromJson(responseBody, type)
                    authTokenResponse.registerToken()
                    callback.onSuccess(authTokenResponse)
                } else {
                    callback.onError("Error registering: ${response.message}")
                }
            }
        })
    }
}