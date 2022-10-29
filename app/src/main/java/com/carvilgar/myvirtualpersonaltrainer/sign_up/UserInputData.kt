package com.carvilgar.myvirtualpersonaltrainer.sign_up

import com.carvilgar.myvirtualpersonaltrainer.sign_in.AppAuthenticationManager

data class UserInputData(
    val name: String,
    val surName: String,
    val birthDate: String,
    val credentials: AppAuthenticationManager,
    val passwordConfirmation: String,
    val height: String,
    val weight: String,
    val activityLevel: Int
) {
}