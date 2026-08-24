package com.hospital.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private boolean success;

    private String message;

    private Map<String, String> errors;

    private LocalDateTime timestamp;
}
