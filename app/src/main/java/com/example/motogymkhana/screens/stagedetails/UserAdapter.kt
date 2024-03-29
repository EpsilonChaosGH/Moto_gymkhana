package com.example.motogymkhana.screens.stagedetails

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.motogymkhana.databinding.ItemUserBinding
import com.example.motogymkhana.model.UserResultState
import com.example.motogymkhana.model.UserStatus

interface UserListener {
}

class UserDiffCallback(
    private val oldList: List<UserResultState>,
    private val newList: List<UserResultState>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList = oldList[oldItemPosition]
        val newList = newList[newItemPosition]
        return oldList.userFullName == newList.userFullName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList = oldList[oldItemPosition]
        val newList = newList[newItemPosition]
        return oldList == newList
    }
}

class UserAdapter(
    private val stageListener: UserListener
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: UserResultState, listener: UserListener) = with(binding) {

            when (item.userStatus) {
                UserStatus.RIDES -> usersNumberTextView.setBackgroundResource(item.userStatus.colorResId)
                UserStatus.NEXT -> usersNumberTextView.setBackgroundResource(item.userStatus.colorResId)
                UserStatus.HEATING -> usersNumberTextView.setBackgroundResource(item.userStatus.colorResId)
                UserStatus.WAITING -> {
                    usersNumberTextView.setTextColor(Color.BLACK)
                    usersNumberTextView.setBackgroundResource(item.userStatus.colorResId)
                }
            }

            userTextView.text = item.userFullName
            userDetailsTextView.text = "${item.champClass} ${item.userCity}"

            groupTextView.text = item.champClass

            usersNumberTextView.text = "48"

            time1TextView.text = item.bestTime
            time2TextView.text = item.bestTime
            time3TextView.text = item.bestTime
        }
    }

    var items = listOf<UserResultState>()
        set(newValue) {
            val diffCallback = UserDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position], stageListener)
    }

    override fun getItemCount(): Int = items.size
}