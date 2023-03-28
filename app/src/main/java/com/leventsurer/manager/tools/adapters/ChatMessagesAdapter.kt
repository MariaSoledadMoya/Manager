package com.leventsurer.manager.tools.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.leventsurer.manager.R
import com.leventsurer.manager.data.model.ChatMessageModel
import com.leventsurer.manager.databinding.UserChatMessageRowBinding
import com.leventsurer.manager.viewModels.AuthViewModel
import kotlinx.coroutines.runBlocking

class ChatMessagesAdapter(private val authViewModel: AuthViewModel) : RecyclerView.Adapter<ChatMessagesAdapter.ChatMessageHolder>(

) {

    private lateinit var context: Context
    class ChatMessageHolder(val binding: UserChatMessageRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    var list = ArrayList<ChatMessageModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageHolder {
        context = parent.context
        val binding =
            UserChatMessageRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatMessageHolder(binding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ChatMessageHolder, position: Int) {
        holder.binding.apply {
            val userName = authViewModel.currentUser!!.displayName
            val currentItem:ChatMessageModel = list[position]
            Log.e("kontrol","size:${list.size}")
            if(currentItem.userName == userName){
                cwMessageCard.setCardBackgroundColor(ContextCompat.getColor(context,R.color.thirdColor))
                twSender.visibility = GONE
                linearLayout1.gravity = Gravity.END
            }else{
                twSender.text = currentItem.userName
            }
            twMessage.text = currentItem.message
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}