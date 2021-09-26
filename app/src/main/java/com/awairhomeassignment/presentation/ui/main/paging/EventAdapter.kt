package com.awairhomeassignment.presentation.ui.main.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.awairhomeassignment.databinding.ListItemEventBinding
import com.awairhomeassignment.domain.model.Event

class EventAdapter : PagingDataAdapter<Event, EventAdapter.ViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemEventBinding.inflate(inflater, parent, false)
        val viewHolder = ViewHolder(binding)

        with(viewHolder.itemView) {
            setOnClickListener {
                if (viewHolder.layoutPosition != RecyclerView.NO_POSITION) {
                    onClick(viewHolder.layoutPosition)
                }
            }
        }

        return viewHolder
    }

    lateinit var onItemClick: (Event) -> Unit

    // Call the click function injected from the outside together with the event.
    private fun onClick(position: Int) {
        if (::onItemClick.isInitialized) {
            val item = getItem(position)
            item?.let { onItemClick(it) }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(
        private val binding: ListItemEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: Event?) {
            binding.model = item
            binding.executePendingBindings()
        }
    }
}