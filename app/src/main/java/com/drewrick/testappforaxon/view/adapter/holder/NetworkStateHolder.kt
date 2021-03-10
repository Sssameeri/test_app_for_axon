package com.drewrick.testappforaxon.view.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.drewrick.testappforaxon.databinding.NetworkStateItemBinding
import com.drewrick.testappforaxon.model.network.NetworkState

class NetworkStateHolder(private val binding: NetworkStateItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(networkState: NetworkState) {
        binding.status = networkState
        binding.executePendingBindings()
    }


}