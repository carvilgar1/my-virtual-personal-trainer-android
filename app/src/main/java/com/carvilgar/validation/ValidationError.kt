package com.carvilgar.validation

class ValidationError<V : Any?> {
    private val errors: HashMap<String, V> = HashMap()

    fun addError(key: String, value: V) {
        errors[key] = value
    }

    fun getErrors(parameterName: String): V? {
        return errors[parameterName]
    }
}