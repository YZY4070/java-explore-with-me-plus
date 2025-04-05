package ru.practicum.stat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.practicum.stat.StatDtoRequest;
import ru.practicum.stat.model.Stat;

@Mapper
public interface StatMapper {
    StatMapper INSTANCE = Mappers.getMapper(StatMapper.class);

    Stat mapToStat(StatDtoRequest statDtoRequest);
}
