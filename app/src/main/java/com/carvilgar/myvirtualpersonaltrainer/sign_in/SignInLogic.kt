package com.carvilgar.myvirtualpersonaltrainer.sign_in

import com.carvilgar.validation.ValidationError

class SignInLogic {

    fun signUpInputTextValidation(email: String, password: String, errors: ValidationError<Any?>) : Boolean {
        val appAuthenticationManager = AppAuthenticationManager(email, password)
        return AppAuthenticationManagerValidator().validate(appAuthenticationManager, errors)
    }
}