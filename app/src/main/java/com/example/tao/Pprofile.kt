package com.example.tao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class Pprofile : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_pprofile, container, false)
        auth = FirebaseAuth.getInstance()

        val editProfileButton = view.findViewById<Button>(R.id.editProfileButton)
        val shareProfileButton = view.findViewById<Button>(R.id.shareProfileButton)
        val delaccButton = view.findViewById<Button>(R.id.delAccButton)
        val logoutButton = view.findViewById<Button>(R.id.logoutButton)


        val message = "Not available at this moment"

        editProfileButton.setOnClickListener {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        shareProfileButton.setOnClickListener {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        delaccButton.setOnClickListener {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        logoutButton.setOnClickListener {
            auth.signOut()
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).replaceFragment(LoginFragment())
        }
        return view
    }
}