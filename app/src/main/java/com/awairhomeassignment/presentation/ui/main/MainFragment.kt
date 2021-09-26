package com.awairhomeassignment.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.awairhomeassignment.databinding.MainFragmentBinding
import com.awairhomeassignment.presentation.ui.SharedViewModel
import com.awairhomeassignment.presentation.ui.main.paging.EventAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private var errorCount = 0

    @Inject
    lateinit var eventAdapter: EventAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        initAdapter()
        initObserve()

        return binding.root
    }

    private fun initAdapter() {
        binding.eventList.adapter = eventAdapter


        eventAdapter.onItemClick = {
            try {
                val action = MainFragmentDirections.actionMainFragmentToEventDetailsFragment(event = it)
                findNavController().navigate(action)

                sharedViewModel.checkEventOverlap(it, eventAdapter.snapshot().items)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        eventAdapter.addLoadStateListener { combinedLoadStates ->
            if (errorCount < 50) {
                var error = combinedLoadStates.refresh is LoadState.Error
                if (error) {
                    eventAdapter.retry()
                    Timber.d("Error Occurred, Tried to attempt 1 more")
                    errorCount++
                } else {
                    error = combinedLoadStates.append is LoadState.Error
                    if (error) {
                        eventAdapter.retry()
                        Timber.d("Error Occurred, Tried to attempt 1 more")
                        errorCount++
                    } else {
                        errorCount = 0
                    }
                }
            }
        }
    }

    private fun initObserve() {
        lifecycleScope.launch {
            viewModel.eventPager.collectLatest { pagedData ->
                eventAdapter.submitData(pagedData)
            }
        }
    }
}