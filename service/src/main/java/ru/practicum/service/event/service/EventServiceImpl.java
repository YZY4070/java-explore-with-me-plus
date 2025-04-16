package ru.practicum.service.event.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.service.category.model.Category;
import ru.practicum.service.category.repository.CategoryRepository;
import ru.practicum.service.event.dto.EventFullDto;
import ru.practicum.service.event.dto.EventShortDto;
import ru.practicum.service.event.dto.NewEventDto;
import ru.practicum.service.event.dto.UpdateEventAdminRequest;
import ru.practicum.service.event.dto.param.EventsForAdminParam;
import ru.practicum.service.event.mapper.EventMapper;
import ru.practicum.service.event.model.Event;
import ru.practicum.service.event.model.Location;
import ru.practicum.service.event.model.QEvent;
import ru.practicum.service.event.model.QLocation;
import ru.practicum.service.event.repository.EventRepository;
import ru.practicum.service.event.repository.LocationRepository;
import ru.practicum.service.exception.NotFoundException;
import ru.practicum.service.user.model.User;
import ru.practicum.service.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    // Admin
    @Override
    public List<EventFullDto> findEventsForAdmin(EventsForAdminParam param) {
        List<BooleanExpression> conditions = new ArrayList<>();
        Pageable pageable = PageRequest.of(param.getFrom() / param.getSize(), param.getSize());

        if (!param.getUsers().isEmpty()) {
            conditions.add(QEvent.event.initiator.id.in(param.getUsers()));
        }
        if (!param.getStates().isEmpty()) {
            conditions.add(QEvent.event.state.in(param.getStates()));
        }
        if (!param.getCategories().isEmpty()) {
            conditions.add(QEvent.event.category.id.in(param.getCategories()));
        }

        if (param.getRangeStart() != null && param.getRangeEnd() != null) {
            conditions.add(QEvent.event.eventDate.between(param.getRangeStart(), param.getRangeEnd()));
        } else if (param.getRangeStart() != null) {
            conditions.add(QEvent.event.eventDate.after(param.getRangeStart()));
        } else if (param.getRangeEnd() != null) {
            conditions.add(QEvent.event.eventDate.between(LocalDateTime.now(), param.getRangeEnd()));
        }

        BooleanExpression finalCondition = !conditions.isEmpty() ? conditions.stream()
                .reduce(BooleanExpression::and)
                .orElse(null) : null;

        if (finalCondition != null) {
            List<Event> events = eventRepository.findAll(finalCondition, pageable).toList();
            return eventMapper.toEventFullDto(events);
        } else {
            return List.of();
        }
    }

    @Override
    @Transactional
    public EventFullDto updateEventForAdmin(Long eventId, UpdateEventAdminRequest event) {
        return null;
    }


    // Private
    @Override
    public List<EventShortDto> findEventsForPrivate(Long userId, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(String.format("Пользователь с id = %d не найден", userId));
        }
        BooleanExpression byUserId = QEvent.event.initiator.id.eq(userId);

        List<Event> events = eventRepository.findAll(byUserId, pageable).toList();
        return eventMapper.toEventShortDto(events);
    }

    @Override
    @Transactional
    public EventFullDto createEventForPrivate(Long userId, NewEventDto newEventDto) {
        Optional<User> initiator = userRepository.findById(userId);
        Optional<Category> category = categoryRepository.findById(newEventDto.getCategory());
        Event event = eventMapper.toEvent(newEventDto);

        event.setInitiator(initiator.orElseThrow(
                () -> new NotFoundException(String.format("Пользователь с id = %d не найден", userId))
        ));
        event.setCategory(category.orElseThrow(
                () -> new NotFoundException(String.format("Категория с id = %d не найдена", newEventDto.getCategory()))
        ));

        locationRepository.save(newEventDto.getLocation());
        eventRepository.save(event);
        EventFullDto dto = eventMapper.toEventFullDto(event);
        dto.setViews(0L);
        return dto;
    }
}
