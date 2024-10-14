package com.example.vendor_android.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R
import com.example.vendor_android.adapters.AccountAdapter
import com.example.vendor_android.modes.SettingOption


class AccountFragment : Fragment() {
    private lateinit var settingsAdapter: AccountAdapter
    private val settingsList = listOf(
        SettingOption("Login and Security"),
        SettingOption("Payment History"),
        SettingOption("Terms and Conditions"),
        SettingOption("Privacy Policy"),
        SettingOption("Customer Support"),
        SettingOption("Service Plans")
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerSettings)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        settingsAdapter = AccountAdapter(settingsList) { setting ->

            Toast.makeText(requireContext(), "Clicked: ${setting.title}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = settingsAdapter

        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()
        }
    }




}