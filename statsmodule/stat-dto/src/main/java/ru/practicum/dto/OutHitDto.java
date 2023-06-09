package ru.practicum.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OutHitDto {
    private Integer id;

    private String app;

    private String uri;

    private String ip;

    private LocalDateTime timestamp;
}
