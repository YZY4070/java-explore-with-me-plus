package ru.practicum.service.error;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.service.exception.ConflictException;
import ru.practicum.service.exception.ForbiddenException;
import ru.practicum.service.exception.NotFoundException;
import ru.practicum.service.exception.ValidationException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(final NotFoundException e) {
        return new ApiError("NOT_FOUND", "The required object was not found.", e.getMessage());
    }

    @ExceptionHandler({ConflictException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflict(RuntimeException e) {
        return new ApiError("CONFLICT", "Integrity constraint has been violated.", e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidation(ValidationException e) {
        return new ApiError("BAD_REQUEST", "Incorrectly made request.", e.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleForbidden(ForbiddenException e) {
        return new ApiError("FORBIDDEN", "For the requested operation the conditions are not met.",
                e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().stream() //извлекаем список всех ошибок
                .map(error -> {
                    String field = ((FieldError) error).getField(); //получаем имя поля, которое не прошло валидацию
                    String message = error.getDefaultMessage(); //получаем сообщение об ошибке
                    Object rejectedValue = ((FieldError) error).getRejectedValue(); //получаем значение не прошедшее валидацию
                    return "Field: " + field + ". Error: " + message + ". Value: " + rejectedValue; //формируем строку с подробным описанием ошибки
                })
                .findFirst()//из всего потока берем первое (если оно есть)
                .orElse("Validation failed without specific error message."); //если по какой-то причине нет подробностей возвращаем дефолтное сообщение

        return new ApiError("BAD_REQUEST", "Incorrectly made request.", errorMessage); //возвращаем объект с информацией об ошибке
    }

}
