package ru.practicum.service;

import dto.InputHitDto;
import dto.OutHitDto;
import dto.OutStatDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.model.Hit;
import ru.practicum.model.StatData;
import ru.practicum.reposiitory.StatServerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class StatServiceImpl implements StatService {

    StatServerRepository repository;

    public StatServiceImpl(StatServerRepository repository) {
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
