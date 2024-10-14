package com.example.vendor_android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R

data class ChatMessage(val message: String, val isSender: Boolean)

class ChatDetailsAdapter(private val messageList: List<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_SENDER = 1
    private val VIEW_TYPE_RECEIVER = 2

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].isSender) VIEW_TYPE_SENDER else VIEW_TYPE_RECEIVER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SENDER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message_sender, parent, false)
            SenderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_messgae_receiver, parent, false)
            ReceiverViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messageList[position]
        if (holder is SenderViewHolder) {
            holder.messageText.text = message.message
        } else if (holder is ReceiverViewHolder) {
            holder.messageText.text = message.message
        }
    }

    override fun getItemCount(): Int = messageList.size

    class SenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageText: TextView = itemView.findViewById(R.id.sender_message_text)
    }

    class ReceiverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageText: TextView = itemView.findViewById(R.id.receiver_message_text)
    }
}
