package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Schema for error response"
)
public class ErrorResponseDto {

    @Schema(
            name = "API Path",
            description = "Path of the API"
    )
    private String apiPath;

    @Schema(
            name = "Error Code",
            description = "HTTP Status Code"
    )
    private HttpStatus errorCode;

    @Schema(
            name = "Error Message",
            description = "Error Message"
    )
    private String errorMessage;

    @Schema(
            name = "Error Date Time",
            description = "Date & Time of the error"
    )
    private LocalDateTime errorTime;
}
