package com.example.final_project_zurab_kobakhidze

import android.util.Patterns

class EmailValidationUseCase {

    fun isValidEmail(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}