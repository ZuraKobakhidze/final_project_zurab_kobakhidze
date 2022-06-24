package com.example.final_project_zurab_kobakhidze

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    //region VIEW
    lateinit var emailText: EditText
    lateinit var passwordText: EditText
    lateinit var logInButton: Button
    lateinit var registerButton: Button
    //endregion

    //region DEPENDENCY
    private lateinit var auth: FirebaseAuth
    private lateinit var emailValidationUseCase: EmailValidationUseCase
    private lateinit var passwordValidationUseCase: PasswordValidationUseCase
    //endregion

    //region CREATION
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        emailText = findViewById(R.id.emailText)
        passwordText = findViewById(R.id.passwordText)
        logInButton = findViewById(R.id.logInButton)
        registerButton = findViewById(R.id.registerButton)

        auth = FirebaseAuth.getInstance()
        emailValidationUseCase = EmailValidationUseCase()
        passwordValidationUseCase = PasswordValidationUseCase()

        logInButton.setOnClickListener {
            onLogIn()
        }

        registerButton.setOnClickListener {
            onRegister()
        }
    }
    //endregion

    //region ACTION
    private fun onLogIn() {

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

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                val activity = Intent(baseContext, HomeActivity::class.java)
                startActivity(activity)
            } else
                Toast.makeText(this, "Log In failed", Toast.LENGTH_SHORT).show()
        }

    }

    private fun onRegister() {
        val activity = Intent(baseContext, RegisterActivity::class.java)
        startActivity(activity)
    }
    //endregion

}