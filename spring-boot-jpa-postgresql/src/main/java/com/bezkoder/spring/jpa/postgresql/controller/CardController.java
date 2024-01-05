package com.bezkoder.spring.jpa.postgresql.controller;

import com.bezkoder.spring.jpa.postgresql.model.Card;
import com.bezkoder.spring.jpa.postgresql.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    CardRepository cardRepository;

    Logger logger = Logger.getLogger(CardController.class.getSimpleName());

    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getAllCards(@RequestParam(required = false) String word) {
        try {
            List<Card> cards = new ArrayList<>();

            if (word == null)
                cards.addAll(cardRepository.findAll());
            else
                cards.addAll(cardRepository.findByWordContaining(word));

            if (cards.isEmpty()) {
                logger.info("Request cards, filtered by :"+word);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id") long id) {
        Optional<Card> cardData = cardRepository.findById(id);

        if (cardData.isPresent()) {
            return new ResponseEntity<>(cardData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cards")
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        try {
            Card _card = cardRepository.save(new Card(card.getWord(), card.getTranslation(), card.getCategory()));
            logger.warning("Creating card: "+ card);
            return new ResponseEntity<>(_card, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cards/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable("id") long id, @RequestBody Card card) {
        Optional<Card> cardData = cardRepository.findById(id);

        if (cardData.isPresent()) {
            Card _card = cardData.get();
            _card.setWord(card.getWord());
            _card.setTranslation(card.getTranslation());
            _card.setCategory(card.getCategory());
            logger.warning("Updating card: "+ card);
            return new ResponseEntity<>(cardRepository.save(_card), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<HttpStatus> deleteCard(@PathVariable("id") long id) {
        try {
            logger.severe("Deleting card: "+ getCardById(id));
            cardRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cards")
    public ResponseEntity<HttpStatus> deleteAllCards() {
        try {
            cardRepository.deleteAll();
            logger.severe("Deleting all cards");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
