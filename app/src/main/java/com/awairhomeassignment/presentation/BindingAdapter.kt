package com.awairhomeassignment.presentation

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.awairhomeassignment.R
import com.awairhomeassignment.domain.model.Event
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a", Locale.US)


//Converts a date in LocalDateTime to a specified pattern.
@BindingAdapter("date")
fun setDate(view: TextView, date: LocalDateTime) {
    val str = date.format(dateTimeFormatter)
    view.text = str
}

//It handles even the case where there is no title.
@BindingAdapter("title")
fun setDate(view: TextView, title: String?) {
    if (title.isNullOrEmpty()) {
        view.text = view.resources.getText(R.string.no_title)
    } else {
        view.text = title
    }
}

//It handles with the case where the end time is earlier than the start time.
@BindingAdapter("check_date")
fun checkDate(view: TextView, event: Event) {

    if (event.end.isBefore(event.start)) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

