package com.example.demoproject.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.demoproject.R
import com.example.demoproject.databinding.FragmentAnyJokesBinding
import com.example.demoproject.ui.activities.adapter.AllJokesAdapter
import com.example.demoproject.view_model.JokesViewModel
import com.example.demoproject.view_model.OfflineJokesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentAnyJokes : Fragment(R.layout.fragment_any_jokes) {

    private lateinit var fragmentAnyJokesBinding: FragmentAnyJokesBinding
    private val TAG: String = "TAG"
    private val jokesViewModel: JokesViewModel by viewModels()
    private val offlineJokesViewModel: OfflineJokesViewModel by viewModels()

    @Inject
    lateinit var allJokesAdapter: AllJokesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentAnyJokesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_any_jokes, container, false)
        return fragmentAnyJokesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = isOnline()

        Log.d(TAG, "onViewCreated: $result")

        if (!result) {

            offlineJokesViewModel.initializeDatabase(requireContext())

            offlineJokesViewModel.getJokesList().observe(viewLifecycleOwner) {
            }

            offlineJokesViewModel.getSavedJokes().observe(viewLifecycleOwner) {
                Log.d(TAG, "onViewCreated: ${it.size}")
                if (it != null) {

                    allJokesAdapter.setJokesList(it)
                }
                fragmentAnyJokesBinding.anyJokesRecyclerView.setHasFixedSize(true)
                fragmentAnyJokesBinding.anyJokesRecyclerView.adapter = allJokesAdapter
            }

        }

        jokesViewModel.getAnyJokes().observe(viewLifecycleOwner) {
            if (it.jokesList != null) {
                allJokesAdapter.setJokesList(it.jokesList)
            }
            fragmentAnyJokesBinding.anyJokesRecyclerView.setHasFixedSize(true)
            fragmentAnyJokesBinding.anyJokesRecyclerView.adapter = allJokesAdapter


        }

        fragmentAnyJokesBinding.gotoProgramming.setOnClickListener {
            val action =
                FragmentAnyJokesDirections.actionFragmentAnyJokesToFragmentProgrammingJokes()
            findNavController().navigate(action)
        }

    }

    private fun isOnline(): Boolean {
        val connectivityManger =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManger.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }


}
