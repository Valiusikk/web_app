package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {

    private String title;

    private String source;

    private Details details;

    private LocalDateTime localDateTime;

}
