package com.carvilgar.myvirtualpersonaltrainer.user

import com.carvilgar.myvirtualpersonaltrainer.authentication.AuthenticationErrors
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

    private fun checkAgeIsInRange(age: Int): Boolean {
        return age in 1..119
    }

    override fun <T> validate(obj: T, errors: ValidationError<Any?>): Boolean {
        val userInputData = obj as UserInputData
        return checkUserNameIsValid(userInputData.name, errors)
                .and(checkUserSurNameIsValid(userInputData.surName, errors))
                .and(checkUserAgeIsValid(userInputData.age, errors))
                .and(checkUserHeightIsValid(userInputData.height, errors))
                .and(checkUserWeightIsValid(userInputData.weight, errors))
    }

    private fun checkUserNameIsValid(name: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotBlank(name)) true
        else {
            errors.addError("name", AuthenticationErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserSurNameIsValid(name: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotBlank(name)) true
        else {
            errors.addError("surName", AuthenticationErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserAgeIsValid(age: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotBlank(age) && age.toInt() in 1..119) {
            true
        } else if (checkParameterIsNotBlank(age)) {
            errors.addError("age", AuthenticationErrors.NOT_VALID_FIELD)
            false
        } else {
            errors.addError("age", AuthenticationErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserHeightIsValid(height: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotBlank(height) && checkHeightIsInRange(height.toFloat())) {
            true
        } else if (checkParameterIsNotBlank(height)) {
            errors.addError("height", AuthenticationErrors.NOT_VALID_FIELD)
            false
        } else {
            errors.addError("height", AuthenticationErrors.EMPTY_FIELD)
            false
        }
    }

    private fun checkUserWeightIsValid(weight: String, errors: ValidationError<Any?>): Boolean {
        return if (checkParameterIsNotBlank(weight) && checkWeightIsInRange(weight.toFloat())) {
            true
        } else if (checkParameterIsNotBlank(weight)) {
            errors.addError("weight", AuthenticationErrors.NOT_VALID_FIELD)
            false
        } else {
            errors.addError("weight", AuthenticationErrors.EMPTY_FIELD)
            false
        }
    }
}