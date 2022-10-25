package com.carvilgar.myvirtualpersonaltrainer.user

import java.util.Date

enum class ActivityLevel {
    SEDENTARY,
    LIGHTLY_ACTIVE,
    MODERATELY_ACTIVE,
    VERY_ACTIVE,
    EXTRA_ACTIVE
}

class User(
    val id: Long?,
    val name: String,
    val surName: String,
    val birthDate: Date,
    var authenticationManager: AppAuthenticationManager?,
    var height: Double,
    var weight: Double,
    var activityLevel: ActivityLevel
){
    public constructor(name: String, surName: String, birthDate: Date,
                       email: String, password: String, height: Double,
                       weight: Double, activityLevel: ActivityLevel) :
            this(null, name, surName, birthDate, AppAuthenticationManager(email, password),
                height, weight, activityLevel)
}