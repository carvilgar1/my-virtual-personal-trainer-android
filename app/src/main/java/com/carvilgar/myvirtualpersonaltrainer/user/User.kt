package com.carvilgar.myvirtualpersonaltrainer.user

import com.carvilgar.myvirtualpersonaltrainer.sign_in.AppAuthenticationManager
import java.text.SimpleDateFormat
import java.util.Date

class User(
    val id: Long?,
    val name: String,
    val surName: String,
    var birthDate: Date?,
    var authenticationManager: AppAuthenticationManager?,
    var height: Float,
    var weight: Float,
    var activityLevel: ActivityLevel
){
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy")

    fun setBirthDate(birthDate: String){
        this.birthDate = dateFormatter.parse(birthDate)
    }
//    constructor(
//        name: String,
//        surName: String,
//        birthDate: String,
//        email: String,
//        password: String,
//        height: String,
//        weight: String,
//        activityLevel: String
//    ) : this(
//        null,
//        name,
//        surName,
//        dateFormatter.parse(birthDate),
//        AppAuthenticationManager(email, password),
//        height.toFloat(),
//        weight.toFloat(),
//        ActivityLevel.valueOf(activityLevel)
//    )
}