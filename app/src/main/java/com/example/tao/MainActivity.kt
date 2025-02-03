package com.example.tao

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tao.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Check if user is already logged in
        if (auth.currentUser != null) {
            // User is logged in, navigate to Home
            replaceFragment(Home())
        } else {
            // User is not logged in, navigate to LoginFragment
            replaceFragment(LoginFragment())
        }

        // Bottom Navigation item selection listener
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(Home())
                R.id.profile -> replaceFragment(Pprofile())
                R.id.settings -> replaceFragment(Settings())
                else -> {}
            }

            true
        }
    }

    // Method to replace the fragments
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (fragment is LoginFragment || fragment is SignUpFragment) {
            // Hide Bottom Navigation when navigating to Login or SignUp fragment
            binding.bottomNavigationView.visibility = View.GONE
        } else {
            // Show Bottom Navigation when navigating to other fragments
            binding.bottomNavigationView.visibility = View.VISIBLE
        }

        // Replace the current fragment with the new fragment
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
