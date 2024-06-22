package com.carvilgar.myvirtualpersonaltrainer.user

data class UserInputData(
    val name: String,
    val surName: String,
    val age: String,
    val height: String,
    val weight: String,
    val activityLevel: Int,
    val maxHeartRate: Int?
) {
}