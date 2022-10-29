package com.carvilgar.myvirtualpersonaltrainer.sign_up

import com.carvilgar.myvirtualpersonaltrainer.sign_in.AppAuthenticationManager
import com.carvilgar.validation.ValidationError

class SingUpLogic {
    fun signUpInputTextValidation(
        name: String, surName: String, birthDate: String, email: String,
        password: String, passwordConfirmation: String, height: String,
        weight: String, errors: ValidationError<Any?>
    ) : Boolean {

        val userInputData = UserInputData(name, surName, birthDate, AppAuthenticationManager(email, password),
            passwordConfirmation, height, weight, 0)
        return UserInputDataValidator().validate(userInputData, errors)
    }
}