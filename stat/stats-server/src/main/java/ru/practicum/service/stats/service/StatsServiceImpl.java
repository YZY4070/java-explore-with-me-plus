package ru.practicum.service.stats.service;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.service.stats.GetStatsRequest;
import ru.practicum.service.stats.StatsDtoRequest;
import ru.practicum.service.stats.StatsDtoResponse;
import ru.practicum.service.stats.mapper.StatsMapper;
import ru.practicum.service.stats.model.QStats;
import ru.practicum.service.stats.model.Stats;
import ru.practicum.service.stats.repository.StatsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatsServiceImpl implements StatsService {
    final StatsRepository statsRepository;
    final EntityManager entityManager;

    @Autowired
    public StatsServiceImpl(StatsRepository statsRepository,
                            EntityManager entityManager) {
        this.statsRepository = statsRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveHit(StatsDtoRequest request) {
        Stats stats = StatsMapper.INSTANCE.mapToStat(request);
        statsRepository.save(stats);
    }

    @Override
    public List<StatsDtoResponse> findStats(GetStatsRequest request) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QStats stats = QStats.stats;

        JPAQuery<StatsDtoResponse> query = queryFactory
                .select(
                        Projections.constructor(StatsDtoResponse.class,
                                stats.app,
                                stats.uri,
                                request.getUnique() ? stats.ip.countDistinct() : stats.ip.count())
                )
                .from(stats)
                .where(stats.timestamp.between(request.getStart(), request.getEnd()))
                .groupBy(stats.app, stats.uri)
                .orderBy(stats.id.count().desc());

        if (request.hasUris()) {
            query.where(stats.uri.in(request.getUris()));
        }

        return query.fetch();
    }
}
