package ru.practicum.service.event.validation.range;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EventMinimumRangeEndValidator.class)
public @interface EventMinimumRangeEnd {
    String message() default "EventsForAdminParam.rangeEnd должно быть позже EventsForAdminParam.rangeStart";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
