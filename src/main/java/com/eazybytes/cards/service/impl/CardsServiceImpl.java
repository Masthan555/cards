package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public CardsDto createCard(CardsDto cardsDto) {
        Cards cards = CardsMapper.mapToCards(cardsDto, new Cards());

        cards.setCardNumber(getUniqueCardNumber().toString());
        cardsRepository.save(cards);

        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        boolean isUpdated = false;
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", cardsDto.getCardNumber().toString()));
        if(cards != null) {
            Cards cardsUpdated = CardsMapper.mapToCards(cardsDto, cards);
            cardsRepository.save(cardsUpdated);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public List<CardsDto> getAllCards(String mobile) {
        return cardsRepository
                .findByMobile(mobile)
                .stream()
                .map(card -> CardsMapper.mapToCardsDto(card, new CardsDto()))
                .toList();
    }

    @Override
    public void deleteCard(Long cardNumber) {
        cardsRepository.findByCardNumber(cardNumber.toString()).orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", cardNumber.toString()));

        cardsRepository.deleteByCardNumber(cardNumber.toString());
    }

    private Long getUniqueCardNumber() {
        return 1000000000L + new Random().nextInt(900000000);
    }
}
