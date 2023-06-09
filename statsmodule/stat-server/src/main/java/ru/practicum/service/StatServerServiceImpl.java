package ru.practicum.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.InputHitDto;
import ru.practicum.dto.OutHitDto;
import ru.practicum.dto.OutStatDto;
import ru.practicum.model.Hit;
import ru.practicum.model.StatData;
import ru.practicum.repositary.StatServerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class StatServerServiceImpl implements StatServerService {

    StatServerRepository repository;

    public StatServerServiceImpl(StatServerRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutHitDto createHit(InputHitDto iHitDto) {
        Hit hit = HitMapper.iHitDtoToHit(iHitDto);
        hit = repository.save(hit);
        log.info("Добавил в лог запрос: {}", hit);
        return HitMapper.hitToOutHitDto(hit);
    }

    @Override
    public List<OutStatDto> getStats(LocalDateTime startDate, LocalDateTime endDate, List<String> uris, Boolean isUnique) {
        List<StatData> tmpList;
        if(isUnique) {
            tmpList = repository.getStatDataWithUniqueIp(uris, startDate, endDate);
        } else {
            tmpList = repository.getStatData(uris, startDate, endDate);
        }
        return tmpList.stream().map(HitMapper::statToDto).collect(Collectors.toList());
    }
}

