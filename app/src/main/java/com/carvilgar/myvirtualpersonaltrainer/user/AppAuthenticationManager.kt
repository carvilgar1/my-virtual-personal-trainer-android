package com.carvilgar.myvirtualpersonaltrainer.user

enum class CheckLoginStatus {
    OK,
    EMAIL_IS_NOT_VALID,
    PASSWORD_IS_NOT_VALID,
    PASSWORD_AND_EMAIL_ARE_NOT_VALID,
}

class AppAuthenticationManager(
    var email: String?,
    var password: String,
){
    private val emailRegex: Regex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    private val passwordRegex: Regex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}\$")

    fun checkEmailIsValid(): Boolean {
        return emailRegex.matches(email?: "")
    }

    fun checkPasswordIsValid(): Boolean {
        return passwordRegex.matches(password?: "")
    }

    fun trySignIn(): CheckLoginStatus{
        return if (checkEmailIsValid() && checkPasswordIsValid()) {
            CheckLoginStatus.OK
        } else {
            if (checkEmailIsValid()) {
                CheckLoginStatus.PASSWORD_IS_NOT_VALID
            } else if (checkPasswordIsValid()) {
                CheckLoginStatus.EMAIL_IS_NOT_VALID
            } else {
                CheckLoginStatus.PASSWORD_AND_EMAIL_ARE_NOT_VALID
            }
        }
    }
}