package com.carvilgar.myvirtualpersonaltrainer.sign_in

import com.carvilgar.myvirtualpersonaltrainer.user.AppAuthenticationManager
import com.carvilgar.myvirtualpersonaltrainer.user.AppAuthenticationManagerValidator

class SignInLogic {

    fun signUpInputTextValidation(email: String, password: String) : Boolean {
        val appAuthenticationManager = AppAuthenticationManager(email, password)
        return AppAuthenticationManagerValidator().validate(appAuthenticationManager)
    }
}