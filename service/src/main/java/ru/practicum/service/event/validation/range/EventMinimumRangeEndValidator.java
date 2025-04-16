package ru.practicum.service.event.validation.range;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.practicum.service.event.dto.param.EventsForAdminParam;

import java.time.LocalDateTime;

public class EventMinimumRangeEndValidator implements ConstraintValidator<EventMinimumRangeEnd, EventsForAdminParam> {

    @Override
    public void initialize(EventMinimumRangeEnd constraintAnnotation) {
    }

    @Override
    public boolean isValid(EventsForAdminParam param, ConstraintValidatorContext context) {
        if (param == null) {
            return false;
        }

        LocalDateTime rangeStart = param.getRangeStart();
        LocalDateTime rangeEnd = param.getRangeEnd();

        if (rangeStart == null || rangeEnd == null) {
            return true;
        }
        return rangeStart.isBefore(rangeEnd);
    }
}
