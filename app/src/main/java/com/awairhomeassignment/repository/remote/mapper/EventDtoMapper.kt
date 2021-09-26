package com.awairhomeassignment.repository.remote.mapper

import com.awairhomeassignment.domain.DomainMapper
import com.awairhomeassignment.domain.model.Event
import com.awairhomeassignment.repository.remote.dto.EventDto
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class EventDtoMapper : DomainMapper<EventDto, Event> {
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a", Locale.US)

    override fun mapFromDomainModel(domainModel: Event): EventDto {

        return EventDto(
            title = domainModel.title,
            start = domainModel.start.format(dateTimeFormatter),
            end = domainModel.end.format(dateTimeFormatter)
        )
    }

    override fun mapToDomainModel(model: EventDto): Event {

        Timber.d("Start ${model.start}")
        val start : LocalDateTime = LocalDateTime.parse(model.start, dateTimeFormatter)
        Timber.d("End ${model.end}")
        val end : LocalDateTime = LocalDateTime.parse(model.end, dateTimeFormatter)

        return Event(
            title = model.title,
            start = start,
            end = end,
        )
    }

    fun toDomainList(initial: List<EventDto>): List<Event> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Event>): List<EventDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}