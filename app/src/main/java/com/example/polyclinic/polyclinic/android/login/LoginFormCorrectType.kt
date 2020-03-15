package com.example.polyclinic.polyclinic.android.login

import androidx.annotation.IntDef

enum class LoginFormCorrectType(val type: String) {
        CORRECT("correct"),
        INCORRECT_USERNAME("incorrect_login"),
        INCORRECT_PASSWORD("incorrect_password"),
        INCORRECT_CONFIRM_PASSWORD("incorrect_confirm_password"),
        FAILED_REGISTER("failed_register");
}