package com.carvilgar.myvirtualpersonaltrainer.user

class UserValidator {
    private fun checkParameterIsNotNullOrEmpty(param: String): Boolean {
        return !param.isNullOrEmpty()
    }

    private fun checkWeightIsInRange(weight: Float): Boolean {
        return weight > 0f && weight < 300f
    }

    private fun checkHeightIsInRange(height: Float): Boolean {
        return height > 0f && height < 3f
    }

}