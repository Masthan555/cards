package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Cards",
        description = "Card details"
)
public class CardsDto {

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile should be 10 digits")
    @Schema(
            name = "Mobile",
            description = "Mobile number of the customer"
    )
    private String mobile;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Card Number should be 10 digits")
    @Schema(
            name = "Card Number",
            description = "Generated Card number"
    )
    private String cardNumber;

    @NotEmpty(message = "Card type should not be empty")
    @Schema(
            name = "Card Type",
            description = "Type of card"
    )
    private String cardType;

    @Positive(message = "Total limit should be positive")
    @NotNull(message = "Total limit should not be empty")
    @Schema(
            name = "Total Limit",
            description = "Total card limit"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Amount Used should be positive or zero")
    @NotNull(message = "Amount Used should not be empty")
    @Schema(
            name = "Amount Used",
            description = "Total Amount used"
    )
    private int amountUsed;

    @Schema(
            name = "Available Amount",
            description = "Total Available amount"
    )
    @Positive(message = "Available amount should be positive")
    @NotNull(message = "Available amount should not be empty")
    private int availableAmount;
}
