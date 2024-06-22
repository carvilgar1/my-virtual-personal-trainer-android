package com.carvilgar.myvirtualpersonaltrainer.sign_up

data class SignUpResponse(
    val errors: Errors?,
    val status: String,
)

data class Errors(
    val email: List<String>?,
    val username: List<String>?
)