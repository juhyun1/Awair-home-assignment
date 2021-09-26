package com.awairhomeassignment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Event(
    val title: String, //Nap Break",
    val start: LocalDateTime, //November 8, 2017 12:30 PM",
    val end: LocalDateTime, //November 8, 2017 1:30 PM"
) : Parcelable
