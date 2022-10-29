package com.carvilgar.myvirtualpersonaltrainer.sign_in

import com.carvilgar.validation.IValidator
import com.carvilgar.validation.ValidationError

class AppAuthenticationManagerValidator : IValidator {
    private val emailRegex: Regex = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    private val passwordRegex: Regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$")

    private fun checkEmailIsValid(email: String): Boolean {
        return emailRegex.matches(email)
    }

    private fun checkPasswordIsValid(password: String): Boolean {
        return passwordRegex.matches(password)
    }

    private fun checkPassIsNotBlank(password: String): Boolean {
        return password.isNotBlank()
    }

    private fun checkEmailIsNotBlank(email: String): Boolean {
        return email.isNotBlank()
    }

    private fun emailEditTextIsValid(email: String, errors: ValidationError<Any?>) : Boolean {
        return if (AppAuthenticationManagerValidator().checkEmailIsNotBlank(email)
            && AppAuthenticationManagerValidator().checkEmailIsValid(email)) {
            true
        } else if (AppAuthenticationManagerValidator().checkEmailIsNotBlank(email)){
            errors.addError("email", SignInErrors.NOT_VALID_FIELD)
            false
        }else {
            errors.addError("email", SignInErrors.EMPTY_FIELD)
            false
        }
    }

    private fun passwordEditTextIsValid(password: String, errors: ValidationError<Any?>) : Boolean {
        return if (AppAuthenticationManagerValidator().checkPassIsNotBlank(password)
            && AppAuthenticationManagerValidator().checkPasswordIsValid(password)) {
            true
        } else if (AppAuthenticationManagerValidator().checkPassIsNotBlank(password)) {
            errors.addError("password", SignInErrors.NOT_VALID_FIELD)
            false
        } else {
            errors.addError("password", SignInErrors.EMPTY_FIELD)
            false
        }
    }

    override fun <T> validate(obj: T, errors: ValidationError<Any?>): Boolean {
        val appAuthenticationManager = obj as AppAuthenticationManager
        return (emailEditTextIsValid(appAuthenticationManager.email, errors).
                    and(passwordEditTextIsValid(appAuthenticationManager.password, errors)))
    }

}