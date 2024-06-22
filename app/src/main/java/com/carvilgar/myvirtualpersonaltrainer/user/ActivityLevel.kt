package com.carvilgar.myvirtualpersonaltrainer.user

enum class ActivityLevel (val code: Int) {
    SEDENTARY(0),
    LIGHTLY_ACTIVE(1),
    MODERATELY_ACTIVE(2),
    VERY_ACTIVE(3);

    override fun toString(): String {
        return when(this) {
            SEDENTARY -> "sedentary"
            LIGHTLY_ACTIVE -> "low_active"
            MODERATELY_ACTIVE -> "active"
            else -> "very_active"
        }
    }
}