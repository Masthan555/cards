package com.eazybytes.cards.controllers;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.dto.ContactInfoDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Cards API's",
        description = "CRUD RESTAPI's for Cards"
)
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class CardsController {

    ICardsService cardsService;
    private final ContactInfoDto contactInfoDto;
    @Autowired
    public CardsController(ICardsService cardsService, ContactInfoDto contactInfoDto) {
        this.cardsService = cardsService;
        this.contactInfoDto = contactInfoDto;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Operation(
            summary = "Create Cards",
            description = "Create Cards record"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Cards created successfully",
            content = @io.swagger.v3.oas.annotations.media.Content
                    (
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CardsDto.class)
                    )
    )
    @PostMapping("/create")
    public ResponseEntity<CardsDto> createCard(@Valid @RequestBody CardsDto cardsDto) {
        CardsDto cards = cardsService.createCard(cardsDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cards);
    }

    @Operation(
            summary = "Update Cards",
            description = "Update Cards record"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cards updated successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Failed to process request"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardsDto cardsDto) {
        boolean isUpdated = cardsService.updateCard(cardsDto);
        if (!isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417));
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }
    }

    @Operation(
            summary = "Get All Cards",
            description = "Get All Cards linked to a customer"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/cards")
    public ResponseEntity<List<CardsDto>> getAllCards(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile should be 10 digits") String mobile) {
        List<CardsDto> cards = cardsService.getAllCards(mobile);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cards);
    }

    @Operation(
            summary = "Delete Card",
            description = "Delete Card of existing customer"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Card deleted successfully"
    )
    @DeleteMapping("/cards/{cardNumber}")
    public ResponseEntity<ResponseDto> deleteCard(@PathVariable @Valid @Pattern(regexp = "(^$|[0-9]{10})", message = "Card Number should be 10 digits") String cardNumber) {
        cardsService.deleteCard(Long.parseLong(cardNumber));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<ContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contactInfoDto);
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }
}
