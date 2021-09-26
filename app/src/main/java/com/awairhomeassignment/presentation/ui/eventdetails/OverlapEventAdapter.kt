package com.awairhomeassignment.presentation.ui.eventdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.awairhomeassignment.databinding.ListItemEventBinding
import com.awairhomeassignment.domain.model.Event

class OverlapEventAdapter : RecyclerView.Adapter<OverlapEventAdapter.ViewHolder>() {

    private var dataSet = mutableListOf<Event>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ListItemEventBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding)
    }

    fun getDataSet(): List<Event> {
        return dataSet
    }
    fun submitData(list : List<Event>){
        dataSet.clear()
        dataSet.addAll(list)
    }

    lateinit var onItemClick: (Event) -> Unit

    private fun onClick(position: Int) {
        if (::onItemClick.isInitialized) {
            val item = dataSet[position]
            onItemClick(item)
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder.itemView) {
            setOnClickListener {
                onClick(position)
            }
        }
        viewHolder.bindTo(dataSet[position])

    }

    class ViewHolder(private val binding: ListItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: Event) {
            binding.model = item
            binding.executePendingBindings()
        }
    }

    override fun getItemCount() = dataSet.size
}
