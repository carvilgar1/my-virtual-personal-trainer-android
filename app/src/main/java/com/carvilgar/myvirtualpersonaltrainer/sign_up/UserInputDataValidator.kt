package com.carvilgar.myvirtualpersonaltrainer.sign_up

import com.carvilgar.myvirtualpersonaltrainer.sign_in.AppAuthenticationManager
import com.carvilgar.myvirtualpersonaltrainer.sign_in.AppAuthenticationManagerValidator
import com.carvilgar.validation.IValidator
import com.carvilgar.validation.ValidationError

class UserInputDataValidator : IValidator {
    //date format: d/M/yyyy
    private val dataRegex = Regex("^\\d{1,2}/\\d{1,2}/\\d{4}$")

    private fun checkParameterIsNotBlank(param: String): Boolean {
        return param.isNotBlank()
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
        val userInputData = obj as UserInputData
        return checkUserNameIsValid(userInputData.name, errors)
                .and(checkUserSurNameIsValid(userInputData.surName, errors))
                .and(checkUserBirthDateIsValid(userInputData.birthDate, errors))
                .and(checkUserHeightIsValid(userInputData.height, errors))
                .and(checkUserWeightIsValid(userInputData.weight, errors))
                .and(checkUserCredentialsAreValid(userInputData.credentials,
                    userInputData.passwordConfirmation,errors))
    }

    private fun checkUserNameIsValid(name: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotBlank(name)) true
        else {
            errors.addError("name", SignUpErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserSurNameIsValid(name: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotBlank(name)) true
        else {
            errors.addError("surName", SignUpErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserBirthDateIsValid(birthDate: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotBlank(birthDate) && checkBirthDateIsCorrect(birthDate)) {
            true
        } else if (checkParameterIsNotBlank(birthDate)) {
            errors.addError("birthDate", SignUpErrors.NOT_VALID_FIELD)
            false
        } else {
            errors.addError("birthDate", SignUpErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserHeightIsValid(height: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotBlank(height) && checkHeightIsInRange(height.toFloat())) {
            true
        } else if (checkParameterIsNotBlank(height)) {
            errors.addError("height", SignUpErrors.NOT_VALID_FIELD)
            false
        } else {
            errors.addError("height", SignUpErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserWeightIsValid(weight: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotBlank(weight) && checkWeightIsInRange(weight.toFloat())) {
            true
        } else if (checkParameterIsNotBlank(weight)) {
            errors.addError("weight", SignUpErrors.NOT_VALID_FIELD)
            false
        } else {
            errors.addError("weight", SignUpErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserCredentialsAreValid(
        credentials: AppAuthenticationManager, password: String,
        errors: ValidationError<Any?>
    ): Boolean {
        return if (AppAuthenticationManagerValidator().validate(credentials, errors) && credentials.password == password) {
            true
        } else if (credentials.password != password) {
            errors.addError("passwordConfirmation", SignUpErrors.PASSWORDS_NOT_MATCH)
            false
        } else {
            false
        }
    }
}