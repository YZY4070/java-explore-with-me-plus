package ru.practicum.stat.service;

import ru.practicum.stat.GetStatRequest;
import ru.practicum.stat.StatDtoRequest;
import ru.practicum.stat.StatDtoResponse;

import java.util.List;

public interface StatService {

    void saveHit(StatDtoRequest request);

    List<StatDtoResponse> findStats(GetStatRequest request);
}
