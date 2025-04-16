package ru.practicum.service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.service.category.dto.CategoryResponseDto;
import ru.practicum.service.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventShortDto {
    String annotation; // Краткое описание события
    CategoryResponseDto category;
    Integer confirmedRequests; // Количество одобренных заявок на участие в данном событии
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate; // Дата и время на которые намечено событие
    Long id;
    UserShortDto initiator;
    Boolean paid; // Нужно ли оплачивать участие
    String title; // Заголовок
    Long views; // Количество просмотров события
}
