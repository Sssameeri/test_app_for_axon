package com.drewrick.testappforaxon.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.drewrick.testappforaxon.model.models.Person
import com.drewrick.testappforaxon.view.adapter.holder.PersonHolder
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.drewrick.testappforaxon.R
import com.drewrick.testappforaxon.databinding.NetworkStateItemBinding
import com.drewrick.testappforaxon.databinding.PersonItemBinding
import com.drewrick.testappforaxon.model.network.NetworkState
import com.drewrick.testappforaxon.view.adapter.holder.NetworkStateHolder
import java.lang.IllegalStateException

class PersonAdapter(val callback: OnItemClickListener) :
    PagedListAdapter<Person, RecyclerView.ViewHolder>(PersonDiffUtil) {

    private var networkState: NetworkState? = null

    object PersonDiffUtil : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.email == newItem.email
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            R.layout.person_item
        }
    }


    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.person_item ->
                PersonHolder(
                    PersonItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            R.layout.network_state_item ->
                NetworkStateHolder(
                    NetworkStateItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.person_item -> {
                val person = getItem(position)
                person?.let {
                    (holder as PersonHolder).bind(it, callback)
                }
            }
            R.layout.network_state_item -> {
                networkState?.let { (holder as NetworkStateHolder).bind(it) }
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(person: Person?)
    }
}