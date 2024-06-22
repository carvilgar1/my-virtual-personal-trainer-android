package com.carvilgar.myvirtualpersonaltrainer.sign_in

import com.carvilgar.myvirtualpersonaltrainer.authentication.UserCredentials
import com.carvilgar.myvirtualpersonaltrainer.authentication.UserCredentialsValidator
import com.carvilgar.validation.ValidationError

class SignInLogic {

    fun signUpInputTextValidation(email: String, password: String, errors: ValidationError<Any?>) : Boolean {
        val appAuthenticationManager = UserCredentials(email, email, email, password)
        return UserCredentialsValidator().validate(appAuthenticationManager, errors)
    }
}