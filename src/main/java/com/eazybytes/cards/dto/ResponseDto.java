package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response"
)
public class ResponseDto {

    @Schema(
            name = "HTTP Status Code"
    )
    private String statusCode;

    @Schema(
            name = "Response Message"
    )
    private String statusMessage;
}
