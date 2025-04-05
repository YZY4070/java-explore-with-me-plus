package ru.practicum.stat;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatDtoRequest {
    String app;
    String uri;
    String ip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    OffsetDateTime timestamp;
}
