package ru.practicum.service;

import ru.practicum.dto.InputHitDto;
import ru.practicum.dto.OutHitDto;
import ru.practicum.dto.OutStatDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatServerService {
    OutHitDto createHit(InputHitDto inputHitDto);

    List<OutStatDto> getStats(LocalDateTime startDate, LocalDateTime endDate, List<String> uris, Boolean isUnique);
}
