package com.example.vendor_android.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R
import com.example.vendor_android.adapters.ChatDetailsAdapter
import com.example.vendor_android.adapters.ChatMessage

class ChatDetailActivity : AppCompatActivity() {

    private lateinit var chatAdapter: ChatDetailsAdapter
    private lateinit var messageList: ArrayList<ChatMessage>
    private lateinit var recyclerView: RecyclerView
    private lateinit var inputMessage: EditText
    private lateinit var buttonSend: ImageView
    private lateinit var backButton: ImageView
    private lateinit var name: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat_detail)

        val chatName = intent.getStringExtra("chat_name")
        val chatMessage = intent.getStringExtra("chat_message")

        recyclerView = findViewById(R.id.recycler_view_chat)
        inputMessage = findViewById(R.id.input_message)
        buttonSend = findViewById(R.id.button_send)
        name = findViewById(R.id.toolbar_title_chat1)
        backButton = findViewById(R.id.back_btn_chat)

        name.text=chatName

        messageList = arrayListOf()

        backButton.setOnClickListener(OnClickListener {
            finish();
        })


        chatAdapter = ChatDetailsAdapter(messageList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatAdapter

        buttonSend.setOnClickListener {
            val message = inputMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                messageList.add(ChatMessage(message, true))
                inputMessage.text.clear()

                chatAdapter.notifyItemInserted(messageList.size - 1)

                recyclerView.scrollToPosition(messageList.size - 1)

                simulateReceiverResponse()
            }
        }


    }

    private fun simulateReceiverResponse() {
        Handler(Looper.getMainLooper()).postDelayed({
            messageList.add(
                ChatMessage(
                    "Received: ${messageList.last().message}",
                    false
                )
            )
            chatAdapter.notifyItemInserted(messageList.size - 1)
            recyclerView.scrollToPosition(messageList.size - 1)
        }, 1000) // Simulate delay for response
    }

}