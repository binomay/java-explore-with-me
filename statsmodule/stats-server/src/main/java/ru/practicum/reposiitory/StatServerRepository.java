package ru.practicum.reposiitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.model.Hit;
import ru.practicum.model.StatData;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatServerRepository extends JpaRepository<Hit, Integer> {
    @Query(value = "SELECT new ru.practicum.model.StatData(h.app, h.uri, count (h.ip)) " +
            " from Hit h where (h.uri in ?1 or ?1 is null) " +
            "and h.created between ?2 and ?3 " +
            "group by h.app, h.uri " +
            "order by count(h.ip) desc")
    List<StatData> getStatData(List<String> uris, LocalDateTime start, LocalDateTime end);

    @Query( value = "SELECT new ru.practicum.model.StatData(h.app, h.uri, count (distinct h.ip)) " +
            " from Hit h where (h.uri in ?1 or ?1 is null) " +
            "and h.created between ?2 and ?3 group by h.app, h.uri " +
            "order by count(distinct h.ip) desc")
    List<StatData> getStatDataWithUniqueIp(List<String> uris, LocalDateTime start, LocalDateTime end);

}
