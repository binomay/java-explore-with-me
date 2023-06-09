package ru.practicum.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.dto.InputHitDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StatClient extends BaseClient {

    private static final String HIT_PREFIX = "/hit";
    private static final String STAT_PREFIX = "/stats";

    @Autowired
    public StatClient(@Value("${stat-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );

    }

    public ResponseEntity<Object> createHit(InputHitDto iHitDto) {
        return post(HIT_PREFIX, iHitDto);
    }

    public ResponseEntity<Object> GetStats(LocalDateTime startDate, LocalDateTime endDate, List<String> uris, Boolean isUnique) {
        Map<String, Object> parameters = Map.of(
                "start", startDate,
                "end", endDate,
                "uris", uris,
                "unique", isUnique);
        return get(STAT_PREFIX, parameters);
    }
}

