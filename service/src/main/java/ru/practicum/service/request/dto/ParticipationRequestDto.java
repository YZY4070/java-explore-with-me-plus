package ru.practicum.service.request.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParticipationRequestDto {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSS")
    LocalDateTime created; // Дата и время создания заявки
    Long event; // Идентификатор события
    Long id;
    Long requester; // Идентификатор пользователя, отправившего заявку
    RequestStatus status; // Статус заявки
}
