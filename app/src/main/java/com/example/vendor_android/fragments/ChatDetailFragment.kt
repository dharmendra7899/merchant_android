package com.example.vendor_android.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R
import com.example.vendor_android.adapters.ChatDetailsAdapter
import com.example.vendor_android.adapters.ChatMessage
import com.example.vendor_android.adapters.ProductAdapter

class ChatDetailFragment : Fragment() {
    private lateinit var chatAdapter: ChatDetailsAdapter
    private lateinit var messageList: ArrayList<ChatMessage>
    private lateinit var recyclerView: RecyclerView
    private lateinit var inputMessage: EditText
    private lateinit var buttonSend: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        inputMessage = view.findViewById(R.id.input_message)
        buttonSend = view.findViewById(R.id.button_send)

        messageList = arrayListOf()

        // Set up RecyclerView with ChatAdapter
        chatAdapter = ChatDetailsAdapter(messageList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = chatAdapter

        // Send message on button click
        buttonSend.setOnClickListener {
            val message = inputMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                // Add new message to list (assuming it's sent by the user)
                messageList.add(ChatMessage(message, true)) // 'true' for sender
                inputMessage.text.clear()

                // Notify adapter about the new message
                chatAdapter.notifyItemInserted(messageList.size - 1)

                // Scroll to the latest message
                recyclerView.scrollToPosition(messageList.size - 1)

                // Simulate a receiver message
                simulateReceiverResponse()
            }
        }
    }


    // Simulate receiver's response (for demonstration)
    private fun simulateReceiverResponse() {
        Handler(Looper.getMainLooper()).postDelayed({
            messageList.add(ChatMessage("Received: ${messageList.last().message}", false)) // 'false' for receiver
            chatAdapter.notifyItemInserted(messageList.size - 1)
            recyclerView.scrollToPosition(messageList.size - 1)
        }, 1000) // Simulate delay for response
    }


}