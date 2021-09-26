package com.awairhomeassignment.presentation.ui.eventdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.awairhomeassignment.databinding.EventDetailsFragmentBinding
import com.awairhomeassignment.presentation.ui.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EventDetailsFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var _binding: EventDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EventDetailsFragmentArgs>()

    @Inject
    lateinit var overlapEventAdapter: OverlapEventAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EventDetailsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.model = args.event
        binding.overlapList.adapter = overlapEventAdapter

        initAdapter()
        initObserve()

        return binding.root
    }

    private fun initAdapter() {

        overlapEventAdapter.onItemClick = {
            try {
                val action = EventDetailsFragmentDirections.actionEventDetailsFragmentToEventDetailsFragment(event = it)
                findNavController().navigate(action)

                sharedViewModel.checkEventOverlap(it, overlapEventAdapter.getDataSet())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun initObserve() {

        sharedViewModel.overlapList.observe(requireActivity()) {
            if (!it.isNullOrEmpty()) {
                binding.overlap.visibility = View.VISIBLE
                overlapEventAdapter.submitData(it)
                overlapEventAdapter.notifyDataSetChanged()
            }
        }
    }
}