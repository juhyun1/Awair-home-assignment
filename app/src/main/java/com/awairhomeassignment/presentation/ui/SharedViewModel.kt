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

    //Deliver overlapping events.
    val overlapList = MutableLiveData<List<Event>>()

    //Check if events overlap,
    fun checkEventOverlap(event: Event, list: List<Event>) {

        viewModelScope.launch {
            val sorted = list.sortedWith { o1, o2 ->
                o1.start.compareTo(o2.start)
            }

            val overlap = mutableListOf<Event>()

            for (item in sorted) {

                //The same event is ignored.
                if (event == item ) {
                    continue
                }

                //Events later than the end time of the event are not compared.
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
