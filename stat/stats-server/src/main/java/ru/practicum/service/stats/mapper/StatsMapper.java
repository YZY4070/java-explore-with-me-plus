package ru.practicum.service.stats.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.practicum.service.stats.StatsDtoRequest;
import ru.practicum.service.stats.model.Stats;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper
public interface StatsMapper {
    StatsMapper INSTANCE = Mappers.getMapper(StatsMapper.class);

    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "toOffsetDateTime")
    @Mapping(target = "id", ignore = true)
    Stats mapToStat(StatsDtoRequest statsDtoRequest);

    @Named("toOffsetDateTime")
    default OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        return OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
    }
}
