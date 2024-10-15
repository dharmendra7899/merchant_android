package com.example.vendor_android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R
import com.example.vendor_android.adapters.ChatAdapter
import com.example.vendor_android.modes.ChatItem

class ChatFragment : Fragment() {

    private lateinit var chatAdapter: ChatAdapter
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.chat_recyclerView)
        searchView = view.findViewById(R.id.searchView)

        searchView.setOnClickListener { searchView.isIconified = false }

        val chatList = listOf(
            ChatItem("It's me", "Ok see you tomorrow", "Yesterday"),
            ChatItem("Prabhat Kumar", "Aaj bhut jyada barish ho rhi hai yrr?", "Yesterday"),
            ChatItem("It's me", "Ok see you tomorrow", "Yesterday"),
            ChatItem("Parbhaaat kumar", "Aaj bhut jyada barish hio rhi hai yrr?", "Yesterday"),
            ChatItem("HR", "Did you check Documents?", "Yesterday"),
            ChatItem("Mohit Sharma", "ok koi nahi", "Yesterday"),
            ChatItem("Alok Kumar", "Did you check Maisie\\'s latest post?", "Today"),
            ChatItem("Nano", "Kaha ho yrr aaye nhi aaj tum", "Yesterday"),
            ChatItem("Abhishek", "Okk chill kro fir", "Yesterday"),
            ChatItem("Rohit", "yee aisa na bolo tum bhut bolti ho ", "Yesterday"),
            ChatItem("Home", "Aaj mai thoda lete se aaunga ", "Yesterday"),
            ChatItem("Prabhat", "I wish GoT had better ending", "Today"),
            ChatItem("Dharmendra", "Ok see you tomorrow", "Yesterday"),
            ChatItem("Home", "Kaha ho yrr aaye nhi aaj tum", "Yesterday"),
            ChatItem("Home", "Did you check Maisie\\'s latest post?", "Today")
        )

        chatAdapter = ChatAdapter(chatList, requireActivity())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = chatAdapter
    }
}
