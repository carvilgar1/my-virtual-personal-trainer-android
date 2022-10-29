package com.carvilgar.myvirtualpersonaltrainer.sign_up

import com.carvilgar.validation.ValidationError
import org.junit.Assert.*
import org.junit.Test

class SignUpTest {
    @Test
    fun signInInputTextValidationSuccess() {
        val email = "prueba.test@gmail.com"
        val password = "m3169y4OVm!8"
        val name = "Carlos"
        val surName = "Villadiego"
        val birthDate = "01/01/2000"
        val height: String = "1.80"
        val weight: String = "80"
        val errors = ValidationError<Any?>()

        val result = SingUpLogic().signUpInputTextValidation(
            name,
            surName,
            birthDate,
            email,
            password,
            password,
            height,
            weight,
            errors
        )

        assertTrue(result)
    }


}