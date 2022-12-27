package com.example.demoproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.demoproject.R
import com.example.demoproject.databinding.FragmentProgrammingJokesBinding
import com.example.demoproject.view_model.JokesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentProgrammingJokes : Fragment(R.layout.fragment_programming_jokes) {

    private lateinit var fragmentProgrammingJokesBinding: FragmentProgrammingJokesBinding

    private val jokesViewModel: JokesViewModel by viewModels()

    @Inject
    lateinit var allJokesAdapter: AllJokesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentProgrammingJokesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_programming_jokes, container, false)
        return fragmentProgrammingJokesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jokesViewModel.getProgrammingJokes().observe(viewLifecycleOwner) {
            if (it.jokesList != null) {
                allJokesAdapter.setJokesList(it.jokesList)
            }
            fragmentProgrammingJokesBinding.ProgrammingJokesRecyclerView.setHasFixedSize(true)
            fragmentProgrammingJokesBinding.ProgrammingJokesRecyclerView.adapter = allJokesAdapter
        }

        fragmentProgrammingJokesBinding.gotoMisc.setOnClickListener {
            val action =
                FragmentProgrammingJokesDirections.actionFragmentProgrammingJokesToFragmentMiscJokes()
            findNavController().navigate(action)
        }

    }

}