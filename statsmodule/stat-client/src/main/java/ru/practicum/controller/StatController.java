package ru.practicum.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.client.StatClient;
import ru.practicum.dto.InputHitDto;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping()
@Validated
public class StatController {

    private final StatClient service;

    public StatController(StatClient service) {
        this.service = service;
    }

    @PostMapping( "/hit" )
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Object> createHit(@RequestBody @Valid InputHitDto inputHitDto) {
        log.info("Логирую запрос: {}", inputHitDto);
        return service.createHit(inputHitDto);
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getBookingsForUser(@RequestParam( name = "start" ) @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" ) LocalDateTime startDate,
                                                     @RequestParam( name = "end" ) @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" ) LocalDateTime endDate,
                                                     @RequestParam( name = "uris", required = false ) ArrayList<String> uris,
                                                     @RequestParam( name = "unique", defaultValue = "false" ) Boolean isUnique) {
        log.info("Запрос статистики за период с {} по {}", startDate, endDate);
        return service.GetStats(startDate, endDate, uris, isUnique);
    }
}

