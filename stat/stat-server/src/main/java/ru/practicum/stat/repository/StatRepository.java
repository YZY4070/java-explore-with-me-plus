package ru.practicum.stat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.stat.model.Stat;

public interface StatRepository extends JpaRepository<Stat, Long> {
}
