package ru.practicum.service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.service.event.model.Location;
import ru.practicum.service.event.validation.date.EventMinimumDate;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewEventDto {
    @NotBlank
    @Size(min = 20, max = 2000)
    String annotation; // Краткое описание события
    @NotNull
    @PositiveOrZero
    Long category; // id категории к которой относится событие
    @NotBlank
    @Size(min = 20, max = 7000)
    String description; // Полное описание события
    @Future
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @EventMinimumDate
    LocalDateTime eventDate; // Дата и время на которые намечено событие
    Location location;
    Boolean paid = false; // Нужно ли оплачивать участие
    @PositiveOrZero
    Integer participantLimit = 0; // Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    Boolean requestModeration = true; // Нужна ли пре-модерация заявок на участие
    @NotBlank
    @Size(min = 3, max = 120)
    String title; // Заголовок
}
