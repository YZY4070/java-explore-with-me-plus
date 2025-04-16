package ru.practicum.service.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import ru.practicum.service.event.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long>,
        QuerydslPredicateExecutor<Location> {

    @Query(value = "INSERT INTO locations (lat, lon) VALUES (:lat, :lon) " +
            "ON CONFLICT (lat, lon) DO UPDATE SET lat = EXCLUDED.lat " +
            "RETURNING *",
            nativeQuery = true)
    Location insertOrUpdateLocation(@Param("lat") Float lat, @Param("lon") Float lon);
}
