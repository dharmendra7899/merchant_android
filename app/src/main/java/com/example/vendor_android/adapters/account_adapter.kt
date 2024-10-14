package com.example.vendor_android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R
import com.example.vendor_android.modes.SettingOption

class AccountAdapter(private val settingsList: List<SettingOption>, private val onItemClick: (SettingOption) -> Unit) :
    RecyclerView.Adapter<AccountAdapter.SettingsViewHolder>() {

    inner class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val settingTitle: TextView = itemView.findViewById(R.id.settingTitle)

        fun bind(setting: SettingOption) {
            settingTitle.text = setting.title
            itemView.setOnClickListener { onItemClick(setting) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.account_item_list, parent, false)
        return SettingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.bind(settingsList[position])
    }

    override fun getItemCount() = settingsList.size
}
