package com.example.demoproject.ui.jokelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.demoproject.R
import com.example.demoproject.databinding.FragmentMiscJokesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentMiscJokes : Fragment(R.layout.fragment_misc_jokes) {

    private lateinit var fragmentMiscJokesBinding: FragmentMiscJokesBinding

    @Inject
    lateinit var allJokesAdapter: AllJokesAdapter

    private val jokesViewModel: JokesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentMiscJokesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_misc_jokes, container, false)
        return fragmentMiscJokesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jokesViewModel.getMiscJokes().observe(viewLifecycleOwner) {

            if (it.jokesList != null) {
                allJokesAdapter.setJokesList(it.jokesList)
            }
            fragmentMiscJokesBinding.miscJokesRecyclerView.setHasFixedSize(true)
            fragmentMiscJokesBinding.miscJokesRecyclerView.adapter = allJokesAdapter
        }

        fragmentMiscJokesBinding.gotoAny.setOnClickListener {
            val action =
            FragmentMiscJokesDirections.actionFragmentMiscJokesToFragmentAnyJokes()
            findNavController().navigate(action)
        }
    }

}