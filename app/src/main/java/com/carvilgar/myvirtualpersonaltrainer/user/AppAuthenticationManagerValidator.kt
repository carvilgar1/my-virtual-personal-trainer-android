package com.carvilgar.myvirtualpersonaltrainer.user

import com.carvilgar.validation.IValidator

class AppAuthenticationManagerValidator : IValidator {
    private val emailRegex: Regex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    private val passwordRegex: Regex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}\$")

    private fun checkEmailIsValid(email: String): Boolean {
        return emailRegex.matches(email)
    }

    private fun checkPasswordIsValid(password: String): Boolean {
        return passwordRegex.matches(password)
    }

    private fun checkPassIsNotEmptyOrNull(password: String): Boolean {
        return !password.isNullOrEmpty()
    }

    private fun checkEmailIsNotEmptyOrNull(email: String): Boolean {
        return !email.isNullOrEmpty()
    }

    private fun emailEditTextIsValid(email: String) : Boolean {
        return if (AppAuthenticationManagerValidator().checkEmailIsNotEmptyOrNull(email)
            && AppAuthenticationManagerValidator().checkEmailIsValid(email)) {
            true
        } else if (AppAuthenticationManagerValidator().checkEmailIsNotEmptyOrNull(email)){
            false
        }else {
            false
        }
    }

    private fun passwordEditTextIsValid(password: String) : Boolean {
        return if (AppAuthenticationManagerValidator().checkPassIsNotEmptyOrNull(password)
            && AppAuthenticationManagerValidator().checkPasswordIsValid(password)) {
            true
        } else if (AppAuthenticationManagerValidator().checkPassIsNotEmptyOrNull(password)) {
            false
        } else {
            false
        }
    }

    override fun <T> validate(obj: T): Boolean {
        val appAuthenticationManager = obj as AppAuthenticationManager
        return (emailEditTextIsValid(appAuthenticationManager.email)
                && passwordEditTextIsValid(appAuthenticationManager.password))
    }

}