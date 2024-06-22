package com.carvilgar.myvirtualpersonaltrainer.authentication

import com.carvilgar.validation.IValidator
import com.carvilgar.validation.ValidationError

class UserCredentialsValidator : IValidator {
    private val emailRegex: Regex = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    private val passwordRegex: Regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$")

    private fun emailEditTextIsValid(email: String, errors: ValidationError<Any?>) : Boolean {
        return if (email.isNotBlank()
            && emailRegex.matches(email)) {
            true
        } else if (email.isNotBlank()){
            errors.addError("email", AuthenticationErrors.NOT_VALID_FIELD)
            false
        }else {
            errors.addError("email", AuthenticationErrors.EMPTY_FIELD)
            false
        }
    }

    private fun passwordEditTextIsValid(password: String, errors: ValidationError<Any?>) : Boolean {
        return if (password.isNotBlank() && passwordRegex.matches(password)) {
            true
        } else if (password.isNotBlank()) {
            errors.addError("password", AuthenticationErrors.NOT_VALID_FIELD)
            false
        } else {
            errors.addError("password", AuthenticationErrors.EMPTY_FIELD)
            false
        }
    }

    private fun passwordsMatches(password: String, confirmedPassword: String,
                                 errors: ValidationError<Any?>) : Boolean {
        return if (password == confirmedPassword) {
            true
        } else {
            errors.addError("password", AuthenticationErrors.PASSWORDS_NOT_MATCH)
            false
        }
    }

    private fun validatesUsername(username: String, errors: ValidationError<Any?>) : Boolean {
        return if (username?.isNullOrEmpty() != false) {
            errors.addError("username", AuthenticationErrors.EMPTY_FIELD)
            false
        } else {
            true
        }
    }

    override fun <T> validate(obj: T, errors: ValidationError<Any?>): Boolean {
        val userCredentials = obj as UserCredentials
        return emailEditTextIsValid(userCredentials.email, errors)
            .and(passwordEditTextIsValid(userCredentials.password, errors))
            .and(passwordsMatches(userCredentials.password, userCredentials.confirmedPassword, errors))
            .and(validatesUsername(userCredentials.username, errors))
    }
}
