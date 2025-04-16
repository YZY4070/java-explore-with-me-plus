package ru.practicum.service.event.controller;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.service.event.dto.EventFullDto;
import ru.practicum.service.event.dto.EventState;
import ru.practicum.service.event.dto.UpdateEventAdminRequest;
import ru.practicum.service.event.dto.param.EventsForAdminParam;
import ru.practicum.service.event.service.EventService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events")
public class EventAdminController {
    private final EventService eventService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventFullDto> findEvents(@RequestParam(required = false) List<Long> users,
                                         @RequestParam(required = false) List<EventState> states,
                                         @RequestParam(required = false) List<Long> categories,
                                         @RequestParam(required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                         @RequestParam(required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                         @RequestParam(defaultValue = "0") Integer from,
                                         @RequestParam(defaultValue = "10") Integer size) {
        return eventService.findEventsForAdmin(
                EventsForAdminParam.of(
                        users,
                        states,
                        categories,
                        rangeStart,
                        rangeEnd,
                        from,
                        size)
        );
    }

    @PatchMapping("/{eventsId}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto updateEvent(@RequestBody @Validated UpdateEventAdminRequest event,
                                    @PathVariable @PositiveOrZero Long eventId) {
        return eventService.updateEventForAdmin(eventId, event);
    }
}
