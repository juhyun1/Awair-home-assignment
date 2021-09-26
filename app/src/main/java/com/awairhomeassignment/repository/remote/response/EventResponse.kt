package com.awairhomeassignment.repository.remote.response

import com.awairhomeassignment.repository.remote.dto.EventDto

data class EventResponse (
    val events: List<EventDto>,
    val next_page_token: String
)