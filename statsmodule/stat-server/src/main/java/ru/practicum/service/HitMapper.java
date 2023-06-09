package ru.practicum.service;

import ru.practicum.dto.InputHitDto;
import ru.practicum.dto.OutHitDto;
import ru.practicum.dto.OutStatDto;
import ru.practicum.model.Hit;
import ru.practicum.model.StatData;

public class HitMapper {
    public static Hit iHitDtoToHit(InputHitDto hitDto) {
        Hit hit = new Hit();
        hit.setApp(hitDto.getApp());
        hit.setIp(hitDto.getIp());
        hit.setUri(hitDto.getUri());
        hit.setCreated(hitDto.getTimestamp());
        return hit;
    }

    public static OutHitDto hitToOutHitDto(Hit hit) {
        OutHitDto dto = new OutHitDto();
        dto.setId(hit.getId());
        dto.setApp(hit.getApp());
        dto.setIp(hit.getIp());
        dto.setUri(hit.getUri());
        dto.setTimestamp(hit.getCreated());
        return dto;
    }

    public static OutStatDto statToDto(StatData statData) {
        OutStatDto dto  = new OutStatDto();
        dto.setApp(statData.getApp());
        dto.setUri(statData.getUri());
        dto.setHits(statData.getHits());
        return dto;
    }

}
