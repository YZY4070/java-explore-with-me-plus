package ru.practicum.service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.service.event.model.Location;
import ru.practicum.service.event.validation.date.EventMinimumDate;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEventAdminRequest {
    @Size(min = 20, max = 2000)
    String annotation; // Новая аннотация
    @PositiveOrZero
    Long category; // Новая категория
    @Size(min = 20, max = 7000)
    String description; // Новое описание
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @EventMinimumDate(amountInHours = 1)
    LocalDateTime eventDate; // Новые дата и время на которые намечено событие
    Location location;
    Boolean paid; // Новое значение флага о платности мероприятия
    @PositiveOrZero
    Integer participantLimit; // Новый лимит пользователей
    Boolean requestModeration; // Нужна ли пре-модерация заявок на участие
    EventStateAction stateAction; // Новое состояние события
    @Size(min = 3, max = 120)
    String title; // Новый заголовок
}
