package ru.practicum.stat.service;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.stat.GetStatRequest;
import ru.practicum.stat.StatDtoRequest;
import ru.practicum.stat.StatDtoResponse;
import ru.practicum.stat.mapper.StatMapper;
import ru.practicum.stat.model.QStat;
import ru.practicum.stat.model.Stat;
import ru.practicum.stat.repository.StatRepository;

import java.util.List;

@Service
public class StatServiceImpl implements StatService {
    StatRepository statRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    public StatServiceImpl(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @Override
    public void saveHit(StatDtoRequest request) {
        Stat stat = StatMapper.INSTANCE.mapToStat(request);
        statRepository.save(stat);
    }

    @Override
    public List<StatDtoResponse> findStats(GetStatRequest request) {
        QStat stat = QStat.stat;

        JPAQuery<StatDtoResponse> query = jpaQueryFactory
                .select(
                        Projections.constructor(StatDtoResponse.class,
                                stat.app,
                                stat.uri,
                                request.getUnique() ? stat.ip.countDistinct() : stat.ip.count())
                )
                .from(stat)
                .where(stat.timestamp.between(request.getStart(), request.getEnd()))
                .groupBy(stat.app, stat.uri)
                .orderBy(stat.id.count().desc());

        if (request.hasUris()) {
            query.where(stat.uri.in(request.getUris()));
        }

        return query.fetch();
    }
}
