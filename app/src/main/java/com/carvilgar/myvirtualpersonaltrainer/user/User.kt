package com.carvilgar.myvirtualpersonaltrainer.user

import com.carvilgar.myvirtualpersonaltrainer.sign_in.AppAuthenticationManager
import java.util.Date

class User(
    val id: Long?,
    val name: String,
    val surName: String,
    val birthDate: Date,
    var authenticationManager: AppAuthenticationManager?,
    var height: Float,
    var weight: Float,
    var activityLevel: ActivityLevel
){

}