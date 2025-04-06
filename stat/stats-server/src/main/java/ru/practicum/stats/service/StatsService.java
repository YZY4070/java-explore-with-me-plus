package ru.practicum.stats.service;

import ru.practicum.stats.GetStatsRequest;
import ru.practicum.stats.StatsDtoRequest;
import ru.practicum.stats.StatsDtoResponse;

import java.util.List;

public interface StatsService {

    void saveHit(StatsDtoRequest request);

    List<StatsDtoResponse> findStats(GetStatsRequest request);
}
