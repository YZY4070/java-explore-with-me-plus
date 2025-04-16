package ru.practicum.service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.service.category.dto.CategoryResponseDto;
import ru.practicum.service.event.model.Location;
import ru.practicum.service.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullDto {
    String annotation; // Краткое описание события
    CategoryResponseDto category;
    Integer confirmedRequests; // Количество одобренных заявок на участие в данном событии
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdOn; // Дата и время создания события
    String description; // Полное описание события
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate; // Дата и время на которые намечено событие
    Long id;
    UserShortDto initiator;
    Location location;
    Boolean paid; // Нужно ли оплачивать участие
    Integer participantLimit; // Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime publishedOn; // Дата и время публикации события
    Boolean requestModeration; // Нужна ли пре-модерация заявок на участие
    EventState state; // Состояние жизненного цикла события
    String title; // Заголовок
    Long views; // Количество просмотров события
}
