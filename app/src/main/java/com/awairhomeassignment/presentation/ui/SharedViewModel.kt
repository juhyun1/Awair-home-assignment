package com.awairhomeassignment.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awairhomeassignment.domain.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel() {

    val overlapList = MutableLiveData<List<Event>>()

    fun checkEventOverlap(event: Event, list: List<Event>) {

        viewModelScope.launch {
            val sorted = list.sortedWith { o1, o2 ->
                o1.start.compareTo(o2.start)
            }

            val overlap = mutableListOf<Event>()

            for (item in sorted) {

                if (event == item ) {
                    continue
                }
                if (event.end.isBefore(item.start)) {
                    break
                }

                if (event.start.isAfter(item.start) && event.start.isBefore(item.end)) {
                    overlap.add(item)
                } else if (event.end.isAfter(item.start) && event.end.isBefore(item.end)) {
                    overlap.add(item)
                }
            }

            overlapList.value = overlap
        }
    }
}
