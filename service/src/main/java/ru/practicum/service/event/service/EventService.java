package ru.practicum.service.event.service;

import ru.practicum.service.event.dto.EventFullDto;
import ru.practicum.service.event.dto.EventShortDto;
import ru.practicum.service.event.dto.NewEventDto;
import ru.practicum.service.event.dto.UpdateEventAdminRequest;
import ru.practicum.service.event.dto.param.EventsForAdminParam;

import java.util.List;

public interface EventService {

    // Admin
    List<EventFullDto> findEventsForAdmin(EventsForAdminParam param);

    EventFullDto updateEventForAdmin(Long eventId, UpdateEventAdminRequest event);

    // Private
    List<EventShortDto> findEventsForPrivate(Long userId, Integer from, Integer size);

    EventFullDto createEventForPrivate(Long userId, NewEventDto newEventDto);
}
