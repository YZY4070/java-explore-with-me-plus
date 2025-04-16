package ru.practicum.service.event.validation.date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class EventMinimumDateValidator implements ConstraintValidator<EventMinimumDate, LocalDateTime> {

    private int timeOffset;

    @Override
    public void initialize(EventMinimumDate constraintAnnotation) {
        this.timeOffset = constraintAnnotation.amountInHours();
    }

    @Override
    public boolean isValid(LocalDateTime eventDate, ConstraintValidatorContext context) {
        if (eventDate == null) {
            return true;
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime min = now.plusHours(timeOffset);

        if (eventDate.isBefore(min) && eventDate.isAfter(now)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Дата мероприятия должна быть не позднее, чем через " + timeOffset + "ч"
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
}
