package com.example.final_project_zurab_kobakhidze

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {

    //region VIEW
    lateinit var emailText: EditText
    lateinit var passwordText: EditText
    lateinit var alreadyHaveAnAccountButton: Button
    lateinit var createAnAccountButton: Button
    //endregion

    //region DEPENDENCY
    private lateinit var auth: FirebaseAuth
    private lateinit var emailValidationUseCase: EmailValidationUseCase
    private lateinit var passwordValidationUseCase: PasswordValidationUseCase
    //endregion

    //region CREATION
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        emailText = findViewById(R.id.emailText)
        passwordText = findViewById(R.id.passwordText)
        alreadyHaveAnAccountButton = findViewById(R.id.alreadyHaveAnAccount)
        createAnAccountButton = findViewById(R.id.createAnAccountButton)

        auth = Firebase.auth

        emailValidationUseCase = EmailValidationUseCase()
        passwordValidationUseCase = PasswordValidationUseCase()

        alreadyHaveAnAccountButton.setOnClickListener {
            onAlreadyHaveAnAccount()
        }

        createAnAccountButton.setOnClickListener {
            onCreateAnAccount()
        }

    }
    //endregion

    //region ACTION
    private fun onAlreadyHaveAnAccount() {
        finish()
    }

    private fun onCreateAnAccount() {

        val email = emailText.text.toString()
        val password = passwordText.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (!emailValidationUseCase.isValidEmail(email)) {
            Toast.makeText(this, "Invalid Email address", Toast.LENGTH_SHORT).show()
            return
        }

        if (!passwordValidationUseCase.isValidPassword(password)) {
            Toast.makeText(this, "Invalid Password try entering more secure", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Created Account", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Creating Account Failed!", Toast.LENGTH_SHORT).show()
            }
        }

    }
    //endregion

}