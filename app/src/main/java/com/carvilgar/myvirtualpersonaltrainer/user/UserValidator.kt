package com.carvilgar.myvirtualpersonaltrainer.user

import com.carvilgar.myvirtualpersonaltrainer.sign_in.AppAuthenticationManagerValidator
import com.carvilgar.validation.IValidator
import com.carvilgar.validation.ValidationError

class UserValidator : IValidator {
    //date format: d/M/yyyy
    private val dataRegex = Regex("^\\d{1,2}/\\d{1,2}/\\d{4}$")

    private fun checkParameterIsNotNullOrEmpty(param: String): Boolean {
        return param.isNotEmpty()
    }

    private fun checkBirthDateIsCorrect(birthDate: String): Boolean {
        return dataRegex.matches(birthDate)
    }

    private fun checkWeightIsInRange(weight: Float): Boolean {
        return weight > 0f && weight < 300f
    }

    private fun checkHeightIsInRange(height: Float): Boolean {
        return height > 0f && height < 3f
    }

    override fun <T> validate(obj: T, errors: ValidationError<Any?>): Boolean {
        val user = obj as User
        return checkUserNameIsValid(user.name, errors)
                .and(checkUserSurNameIsValid(user.surName, errors))
                //.and(checkUserBirthDateIsValid(obj as User, errors))
                .and(checkUserHeightIsValid(user.height, errors))
                .and(checkUserWeightIsValid(user.height, errors))
                .and(AppAuthenticationManagerValidator().validate(user.authenticationManager?:null, errors))
    }

    private fun checkUserNameIsValid(name: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotNullOrEmpty(name)) true
        else {
            errors.addError("name", SignUpErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserSurNameIsValid(name: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotNullOrEmpty(name)) true
        else {
            errors.addError("surName", SignUpErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserBirthDateIsValid(name: String, errors: ValidationError<SignUpErrors>): Boolean {
        return if (checkParameterIsNotNullOrEmpty(name) && checkBirthDateIsCorrect(name)) {
            true
        } else if (checkParameterIsNotNullOrEmpty(name)) {
            errors.addError("birthDate", SignUpErrors.NOT_VALID_FIELD)
            false
        } else {
            errors.addError("birthDate", SignUpErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserHeightIsValid(height: Float, errors: ValidationError<Any?>): Boolean {
        return if (height != null && checkHeightIsInRange(height)) {
            true
        } else if (height != null) {
            errors.addError("height", SignUpErrors.NOT_VALID_FIELD)
            false
        } else {
            errors.addError("height", SignUpErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserWeightIsValid(weight: Float, errors: ValidationError<Any?>): Boolean {
        return if (weight != null && checkWeightIsInRange(weight)) {
            true
        } else if (weight != null) {
            errors.addError("weight", SignUpErrors.NOT_VALID_FIELD)
            false
        } else {
            errors.addError("weight", SignUpErrors.EMPTY_FIELD)
            false
        }
    }
}