package com.carvilgar.validation

interface IValidator {
    fun <T> validate(obj: T, errors: ValidationError<Any?>): Boolean
}