package ru.practicum.service.event.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.practicum.service.category.model.Category;
import ru.practicum.service.event.dto.EventFullDto;
import ru.practicum.service.event.dto.EventShortDto;
import ru.practicum.service.event.dto.NewEventDto;
import ru.practicum.service.event.model.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "state", constant = "PENDING"),
            @Mapping(target = "confirmedRequests", constant = "0L"),
            @Mapping(target = "createdOn", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "publishedOn", ignore = true),
            @Mapping(target = "initiator", ignore = true),
            @Mapping(target = "category", expression = "java(toCategory(newEventDto.getCategory()))"),
            @Mapping(target = "views", ignore = true)
    })
    Event toEvent(NewEventDto newEventDto);

    EventFullDto toEventFullDto(Event event);

    List<EventFullDto> toEventFullDto(List<Event> events);

    EventShortDto toEventShortDto(Event event);

    List<EventShortDto> toEventShortDto(List<Event> events);

    default Category toCategory(Long id) {
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
