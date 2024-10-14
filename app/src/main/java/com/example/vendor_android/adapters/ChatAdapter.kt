package com.example.vendor_android.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R
import com.example.vendor_android.fragments.ChatDetailFragment
import com.example.vendor_android.modes.ChatItem


class ChatAdapter(private val chatList: List<ChatItem>,private val fragment: Fragment) :
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

        holder.chatAvatar.setImageResource(R.drawable.app_logo)

        holder.itemView.setOnClickListener {
            fragment.parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ChatDetailFragment())
                .addToBackStack(null) // Optional: Add to back stack
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chatAvatar: ImageView = itemView.findViewById(R.id.chat_avatar)
        val chatName: TextView = itemView.findViewById(R.id.chat_name)
        val chatMessage: TextView = itemView.findViewById(R.id.chat_message)
        val chatTime: TextView = itemView.findViewById(R.id.chat_time)
    }
}
