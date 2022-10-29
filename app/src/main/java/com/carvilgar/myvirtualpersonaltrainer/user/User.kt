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
}