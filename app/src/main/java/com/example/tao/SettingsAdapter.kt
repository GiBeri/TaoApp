package com.example.tao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SettingsAdapter(
    private val settingsList: List<SettingsItem>,
    private val itemClickListener: (SettingsItem) -> Unit
) : RecyclerView.Adapter<SettingsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.settingIcon)
        val title: TextView = view.findViewById(R.id.settingTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.settings_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val setting = settingsList[position]
        holder.icon.setImageResource(setting.iconRes)
        holder.title.text = setting.title

        holder.itemView.setOnClickListener { itemClickListener(setting) }
    }

    override fun getItemCount() = settingsList.size
}
