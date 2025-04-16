package ru.practicum.service.event.dto.param;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.service.event.dto.EventState;
import ru.practicum.service.event.validation.range.EventMinimumRangeEnd;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EventMinimumRangeEnd
public class EventsForAdminParam {
    List<Long> users;
    List<EventState> states;
    List<Long> categories;
    LocalDateTime rangeStart;
    LocalDateTime rangeEnd;
    Integer from;
    Integer size;

    public static EventsForAdminParam of(List<Long> users,
                                         List<EventState> states,
                                         List<Long> categories,
                                         LocalDateTime rangeStart,
                                         LocalDateTime rangeEnd,
                                         Integer from,
                                         Integer size) {
        EventsForAdminParam param = new EventsForAdminParam();
        param.setUsers(users);
        param.setStates(states);
        param.setCategories(categories);
        param.setRangeStart(rangeStart);
        param.setRangeEnd(rangeEnd);
        param.setFrom(from);
        param.setSize(size);

        return param;
    }
}
