package ru.practicum.stat.controller;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.stat.GetStatRequest;
import ru.practicum.stat.StatDtoRequest;
import ru.practicum.stat.StatDtoResponse;
import ru.practicum.stat.service.StatService;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@RestController
public class StatController {
    final StatService statService;

    @Autowired
    public StatController(StatService statService) {
        this.statService = statService;
    }

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void hit(@RequestBody StatDtoRequest request) {
        log.info("Запрос на добавление данных в статистику");
        statService.saveHit(request);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public List<StatDtoResponse> findStats(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @NotNull OffsetDateTime start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @NotNull OffsetDateTime end,
            @RequestParam(name = "uris", required = false) List<String> uris,
            @RequestParam(name = "unique", defaultValue = "false") Boolean unique) {
        log.info("Запрос на получение статистики");
        log.info("Параметры: \nstart = {} \nend = {} \nuris = {} \nunique = {}", start, end, uris, unique);
        return statService.findStats(GetStatRequest.of(start, end, uris, unique));
    }
}
