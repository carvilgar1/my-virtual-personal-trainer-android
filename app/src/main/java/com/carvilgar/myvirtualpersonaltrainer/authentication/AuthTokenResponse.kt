package com.carvilgar.myvirtualpersonaltrainer.authentication

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class AuthTokenResponse(
    val accessToken: String,
    val expiresIn: Long,
    val refreshToken: String,
    val tokenType: String,
    var creationDateTime: LocalDateTime
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun registerToken() {
        creationDateTime = LocalDateTime.now()
    }
}
