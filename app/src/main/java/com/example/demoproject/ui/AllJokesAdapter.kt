package com.example.demoproject.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject.R
import com.example.demoproject.api_network.jokedto.JokesSecondaryModel

import com.example.demoproject.databinding.JokesCategoryBinding
import javax.inject.Inject

class AllJokesAdapter @Inject constructor() :
    RecyclerView.Adapter<AllJokesAdapter.AnyJokesViewHolder>() {

    private var jokesList: MutableList<JokesSecondaryModel> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setJokesList(list: List<JokesSecondaryModel>) {
        this.jokesList.clear()
        this.jokesList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnyJokesViewHolder {
        val binding: JokesCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.jokes_category,
            parent,
            false
        )
        return AnyJokesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnyJokesViewHolder, position: Int) {
        holder.bind(jokesList[position])
    }

    override fun getItemCount(): Int {
        return jokesList.size
    }


    class AnyJokesViewHolder(private val binding: JokesCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: JokesSecondaryModel) {

            val type = item.type

            if (type.equals("twopart")) {
                binding.setup.text = item.setUp
                binding.delivery.text = item.delivery
            } else {
                binding.setup.text = item.Jokes
                binding.delivery.visibility = View.GONE
            }
        }
    }
}