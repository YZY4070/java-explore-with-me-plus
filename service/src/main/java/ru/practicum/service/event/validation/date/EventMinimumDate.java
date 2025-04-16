package ru.practicum.service.event.validation.date;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EventMinimumDateValidator.class)
public @interface EventMinimumDate {
    String message() default "До даты мероприятия должно быть не менее 2 часов";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int amountInHours() default 2;
}
