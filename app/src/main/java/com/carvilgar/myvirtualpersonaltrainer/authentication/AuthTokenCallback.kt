package com.carvilgar.myvirtualpersonaltrainer.authentication

interface AuthTokenCallback {
    fun onSuccess(authTokenResponse: AuthTokenResponse)
    fun onError(errorMessage: String)
}
