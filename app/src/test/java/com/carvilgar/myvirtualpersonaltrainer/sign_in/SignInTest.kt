package com.carvilgar.myvirtualpersonaltrainer.sign_in

import com.carvilgar.myvirtualpersonaltrainer.authentication.SignInLogic
import com.carvilgar.validation.ValidationError
import org.junit.Assert.*
import org.junit.Test

class SignInTest {
    @Test
    fun signInInputTextValidationSuccess() {
        val email = "prueba.test@gmail.com"
        val password = "m3169y4OVm!8"
        val errors = ValidationError<Any?>()

        val result = SignInLogic().signUpInputTextValidation(email, password, errors)

        assertTrue(result)
    }

    @Test
    fun signInInputTextValidationEmailEmpty() {
        val email = ""
        val password = "m3169y4OVm!8"
        val errors = ValidationError<Any?>()

        val result = SignInLogic().signUpInputTextValidation(email, password, errors)
        assertFalse(result)
        assertNotNull(errors.getErrors("email"))
        assertNull(errors.getErrors("password"))
        assertEquals(SignInErrors.EMPTY_FIELD, errors.getErrors("email"))
    }

    @Test
    fun signInInputTextValidationPassEmpty() {
        val email = "prueba.test@gmail.com"
        val password = ""
        val errors = ValidationError<Any?>()

        val result = SignInLogic().signUpInputTextValidation(email, password, errors)
        assertFalse(result)
        assertNotNull(errors.getErrors("password"))
        assertNull(errors.getErrors("email"))
        assertEquals(SignInErrors.EMPTY_FIELD, errors.getErrors("password"))
    }

    @Test
    fun signInInputTextValidationPassAndEmailEmpty() {
        val email = "carlitos@.com"
        val password = "12345"
        val errors = ValidationError<Any?>()

        val result = SignInLogic().signUpInputTextValidation(email, password, errors)
        assertFalse(result)
        assertNotNull(errors.getErrors("password"))
        assertNotNull(errors.getErrors("email"))
        assertEquals(SignInErrors.NOT_VALID_FIELD, errors.getErrors("password"))
        assertEquals(SignInErrors.NOT_VALID_FIELD, errors.getErrors("email"))
    }
}