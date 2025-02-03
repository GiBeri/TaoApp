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

class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()

        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val usernameEditText = view.findViewById<EditText>(R.id.usernameEditText)
        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val registerButton = view.findViewById<Button>(R.id.registerButton)
        val goToLoginButton = view.findViewById<Button>(R.id.goToLoginButton)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Registration successful
                            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                            (activity as MainActivity).replaceFragment(LoginFragment())
                        } else {
                            // Registration failed
                            Toast.makeText(context, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Go to Login
        goToLoginButton.setOnClickListener {
            (activity as MainActivity).replaceFragment(LoginFragment())
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }
}
