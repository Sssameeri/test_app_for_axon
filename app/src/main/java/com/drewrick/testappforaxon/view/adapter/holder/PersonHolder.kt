package com.drewrick.testappforaxon.view.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.drewrick.testappforaxon.databinding.PersonItemBinding
import com.drewrick.testappforaxon.model.models.Person
import com.drewrick.testappforaxon.view.adapter.PersonAdapter

class PersonHolder(private val binding: PersonItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(person: Person, callback: PersonAdapter.OnItemClickListener) {
        binding.person = person
        binding.callback = callback
        binding.executePendingBindings()
    }
}
