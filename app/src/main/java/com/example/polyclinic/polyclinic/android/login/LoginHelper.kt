package com.example.polyclinic.polyclinic.android.login

import java.util.*

object LoginHelper {

    @JvmStatic
    fun handleRegisterData(
        username: String,
        password: String,
        confirmPassword: String
    ): LoginFormCorrectType {
        val correctType = handleLoginData(username, password)
        return if (correctType == LoginFormCorrectType.CORRECT && password != confirmPassword) {
            LoginFormCorrectType.INCORRECT_CONFIRM_PASSWORD
        } else {
            LoginFormCorrectType.CORRECT
        }
    }

    @JvmStatic
    fun handleLoginData(
        username: String,
        password: String
    ): LoginFormCorrectType {
        if (username.length < 8 || username.length > 32) {
            return LoginFormCorrectType.INCORRECT_USERNAME
        }

        if (password.length < 8 || password.length > 32) {
            return LoginFormCorrectType.INCORRECT_PASSWORD
        }
        return LoginFormCorrectType.CORRECT
    }
}