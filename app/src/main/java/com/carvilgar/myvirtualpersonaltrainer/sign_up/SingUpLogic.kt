package com.carvilgar.myvirtualpersonaltrainer.sign_up

import com.carvilgar.myvirtualpersonaltrainer.sign_in.AppAuthenticationManager
import com.carvilgar.myvirtualpersonaltrainer.sign_in.AppAuthenticationManagerValidator
import com.carvilgar.myvirtualpersonaltrainer.user.ActivityLevel
import com.carvilgar.myvirtualpersonaltrainer.user.User
import com.carvilgar.myvirtualpersonaltrainer.user.UserValidator
import com.carvilgar.validation.ValidationError

class SingUpLogic {
    fun signUpInputTextValidation(email: String, password: String, errors: ValidationError<Any?>) : Boolean {
        val user = User(null, "", "", null, AppAuthenticationManager(email, password), 0f, 0f, ActivityLevel.SEDENTARY)
        return UserValidator().validate(user, errors)
    }
}