package ru.practicum.service.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.practicum.service.event.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>,
        QuerydslPredicateExecutor<Event> {
}
