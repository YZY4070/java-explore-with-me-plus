package ru.practicum.stat;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatDtoResponse {
    String app;
    String uri;
    Long hits;
}
