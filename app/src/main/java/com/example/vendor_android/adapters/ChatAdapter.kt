package com.example.vendor_android.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R
import com.example.vendor_android.activities.ChatDetailActivity
import com.example.vendor_android.fragments.ChatDetailFragment
import com.example.vendor_android.modes.ChatItem

class ChatAdapter(private val chatList: List<ChatItem>, private val activity: FragmentActivity) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_list, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatItem = chatList[position]
        holder.chatName.text = chatItem.name
        holder.chatMessage.text = chatItem.lastMessage
        holder.chatTime.text = chatItem.time

        // Set placeholder avatar
        holder.chatAvatar.setImageResource(R.drawable.app_logo)

        // Set item click listener
        holder.listItem.setOnClickListener {
            val intent = Intent(activity, ChatDetailActivity::class.java)
            intent.putExtra("chat_name", chatItem.name)
            intent.putExtra("chat_message", chatItem.lastMessage)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = chatList.size

    // ViewHolder to hold chat item views
    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chatAvatar: ImageView = itemView.findViewById(R.id.chat_avatar)
        val chatName: TextView = itemView.findViewById(R.id.chat_name)
        val chatMessage: TextView = itemView.findViewById(R.id.chat_message)
        val chatTime: TextView = itemView.findViewById(R.id.chat_time)
        val listItem: LinearLayout = itemView.findViewById(R.id.chatLin)
    }
}

