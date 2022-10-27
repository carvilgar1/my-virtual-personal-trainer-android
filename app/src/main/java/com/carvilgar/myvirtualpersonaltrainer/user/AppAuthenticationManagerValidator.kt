package com.carvilgar.myvirtualpersonaltrainer.user

class AppAuthenticationManagerValidator {
    private val emailRegex: Regex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    private val passwordRegex: Regex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}\$")

    fun checkEmailIsValid(email: String): Boolean {
        return emailRegex.matches(email)
    }

    fun checkPasswordIsValid(password: String): Boolean {
        return passwordRegex.matches(password)
    }

    fun checkPassIsNotEmptyOrNull(password: String): Boolean {
        return !password.isNullOrEmpty()
    }

    fun checkEmailIsNotEmptyOrNull(email: String): Boolean {
        return !email.isNullOrEmpty()
    }

}