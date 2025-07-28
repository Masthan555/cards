package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDto;

import java.util.List;

public interface ICardsService {
    CardsDto createCard(CardsDto loansDto);

    boolean updateCard(CardsDto loansDto);

    List<CardsDto> getAllCards(String mobile);

    void deleteCard(Long loanNumber);
}
