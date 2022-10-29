package com.carvilgar.myvirtualpersonaltrainer.sign_in

import com.carvilgar.validation.IValidator
import com.carvilgar.validation.ValidationError

class AppAuthenticationManagerValidator : IValidator {
    private val emailRegex: Regex = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    private val passwordRegex: Regex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}\$")

    private fun checkEmailIsValid(email: String): Boolean {
        return emailRegex.matches(email)
    }

    private fun checkPasswordIsValid(password: String): Boolean {
        return passwordRegex.matches(password)
    }

    private fun checkPassIsNotEmptyOrNull(password: String): Boolean {
        return password.isNotEmpty()
    }

    private fun checkEmailIsNotEmptyOrNull(email: String): Boolean {
        return email.isNotEmpty()
    }

    private fun emailEditTextIsValid(email: String, errors: ValidationError<Any?>) : Boolean {
        return if (AppAuthenticationManagerValidator().checkEmailIsNotEmptyOrNull(email)
            && AppAuthenticationManagerValidator().checkEmailIsValid(email)) {
            true
        } else if (AppAuthenticationManagerValidator().checkEmailIsNotEmptyOrNull(email)){
            errors.addError("password", SignInErrors.EMPTY_FIELD)
            false
        }else {
            errors.addError("password", SignInErrors.NOT_VALID_FIELD)
            false
        }
    }

    private fun passwordEditTextIsValid(password: String, errors: ValidationError<Any?>) : Boolean {
        return if (AppAuthenticationManagerValidator().checkPassIsNotEmptyOrNull(password)
            && AppAuthenticationManagerValidator().checkPasswordIsValid(password)) {
            true
        } else if (AppAuthenticationManagerValidator().checkPassIsNotEmptyOrNull(password)) {
            errors.addError("password", SignInErrors.EMPTY_FIELD)
            false
        } else {
            errors.addError("password", SignInErrors.NOT_VALID_FIELD)
            false
        }
    }

    override fun <T> validate(obj: T, errors: ValidationError<Any?>): Boolean {
        val appAuthenticationManager = obj as AppAuthenticationManager
        return (emailEditTextIsValid(appAuthenticationManager.email, errors)
                && passwordEditTextIsValid(appAuthenticationManager.password, errors))
    }

}