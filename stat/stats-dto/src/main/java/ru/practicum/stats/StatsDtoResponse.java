package ru.practicum.stats;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatsDtoResponse {
    String app;
    String uri;
    Long hits;
}
