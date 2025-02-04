package com.example.tao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.settingsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val settingsList = listOf(
            SettingsItem("Account", R.drawable.ic_account),
            SettingsItem("Notifications", R.drawable.ic_notifications),
            SettingsItem("Privacy", R.drawable.ic_privacy),
            SettingsItem("Language", R.drawable.ic_language),
            SettingsItem("Help & Support", R.drawable.ic_help),
            SettingsItem("Logout", R.drawable.ic_logout)
        )

        recyclerView.adapter = SettingsAdapter(settingsList) { item ->
            handleItemClick(item)
        }

        return view
    }

    private fun handleItemClick(item: SettingsItem) {
        when (item.title) {
            "Logout" -> {
                (activity as MainActivity).logoutUser()
            }
            else -> {
                // Handle other settings options
            }
        }
    }
}
