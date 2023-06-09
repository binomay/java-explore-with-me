package ru.practicum.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.InputHitDto;
import ru.practicum.dto.OutHitDto;
import ru.practicum.dto.OutStatDto;
import ru.practicum.service.StatServerService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping()
public class StatServerController {

    private final StatServerService service;

    public StatServerController(StatServerService service) {
        this.service = service;
    }

    @PostMapping( "/hit" )
    @ResponseStatus( code = HttpStatus.CREATED )
    public OutHitDto createHit(@RequestBody InputHitDto inputHitDto) {
        log.info("Логирую запрос: {}", inputHitDto);
        return service.createHit(inputHitDto);
    }

    @GetMapping( "/stats" )
    public List<OutStatDto> getBookingsForUser(@RequestParam( name = "start" ) @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" ) LocalDateTime startDate,
                                               @RequestParam( name = "end" ) @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" ) LocalDateTime endDate,
                                               @RequestParam( name = "uris", required = false ) ArrayList<String> uris,
                                               @RequestParam( name = "unique", defaultValue = "false" ) Boolean isUnique) {
        log.info("Запрос статистики за период с {} по {}", startDate, endDate);
        return service.getStats(startDate, endDate, uris, isUnique);
    }
}
