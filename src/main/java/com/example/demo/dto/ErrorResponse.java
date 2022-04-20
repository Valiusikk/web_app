package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ErrorResponse {

    private String title;

    private String source;

    private Details details;

    private LocalDateTime localDateTime;

}
