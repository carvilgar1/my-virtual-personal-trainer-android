package com.carvilgar.validation

interface IValidator {
    fun <T> validate(obj: T): Boolean
}