package ru.practicum.service;

import dto.InputHitDto;
import dto.OutHitDto;
import dto.OutStatDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatService {
    OutHitDto createHit(InputHitDto inputHitDto);

    List<OutStatDto> getStats(LocalDateTime startDate, LocalDateTime endDate, List<String> uris, Boolean isUnique);
}
