package com.exercise.tbchomeworkfourteen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exercise.tbchomeworkfourteen.databinding.ItemOneLayoutBinding
import com.exercise.tbchomeworkfourteen.databinding.ItemTwoLayoutBinding
import com.exercise.tbchomeworkfourteen.model.ModelInfo

class ItemModelAdapter(private val listener: Listener) :
    ListAdapter<ModelInfo, RecyclerView.ViewHolder>(ItemDiffCallBack()) {

    interface Listener {
        fun deleteCurrentItem(model: ModelInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ModelInfo.ModelGender.FEMALE.ordinal -> {
                val binding = ItemOneLayoutBinding.inflate(inflater, parent, false)
                ItemOneViewHolder(binding)
            }

            ModelInfo.ModelGender.MALE.ordinal -> {
                val binding = ItemTwoLayoutBinding.inflate(inflater, parent, false)
                ItemTwoViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is ItemOneViewHolder) {
            holder.bind(item)
        } else if (holder is ItemTwoViewHolder) {
            holder.bind(item)
        }
    }


    inner class ItemOneViewHolder(private val binding: ItemOneLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ModelInfo) {
            binding.apply {
                ivImage.setImageResource(item.image)
                tvName.text = item.name
                tvHeight.text = item.height.toString()
                binding.btnDelete.setOnClickListener {
                    listener.deleteCurrentItem(item)
                }
            }
        }
    }

    inner class ItemTwoViewHolder(private val binding: ItemTwoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ModelInfo) {
            binding.apply {
                ivImage.setImageResource(item.image)
                tvName.text = item.name
                tvHeight.text = item.height.toString()
                binding.btnDelete.setOnClickListener {
                    listener.deleteCurrentItem(item)
                }
            }
        }
    }


    class ItemDiffCallBack : DiffUtil.ItemCallback<ModelInfo>() {
        override fun areItemsTheSame(oldItem: ModelInfo, newItem: ModelInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ModelInfo, newItem: ModelInfo): Boolean {
            return oldItem == newItem
        }
    }
}