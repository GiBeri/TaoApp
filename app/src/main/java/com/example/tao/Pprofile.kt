package com.example.tao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast

class Pprofile : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pprofile, container, false)

        // Find the logout button in the layout
        val logoutButton = view.findViewById<Button>(R.id.logoutButton)

        // Set OnClickListener for the logout button
        logoutButton.setOnClickListener {
            // Log out the user from Firebase
            auth.signOut()

            // Show a toast to notify the user
            Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()

            // Hide the bottom navigation after logging out
            (activity as MainActivity).binding.bottomNavigationView.visibility = View.GONE

            // Navigate to the LoginFragment
            replaceFragment(LoginFragment())
        }

        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = Pprofile()
    }
}
