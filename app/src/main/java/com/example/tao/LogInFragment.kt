package com.example.tao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance()
        val view = inflater.inflate(R.layout.fragment_log_in, container, false)

        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val loginButton = view.findViewById<Button>(R.id.loginButton)
        val goToSignUpButton = view.findViewById<Button>(R.id.goToSignUpButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).replaceFragment(Home())
                    } else {
                        Toast.makeText(context, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Please enter your email and password", Toast.LENGTH_SHORT).show()
            }
        }

        goToSignUpButton.setOnClickListener {
            (activity as MainActivity).replaceFragment(SignUpFragment())
        }
        return view
    }
}
