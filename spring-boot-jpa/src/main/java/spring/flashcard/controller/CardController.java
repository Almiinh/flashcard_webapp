package spring.flashcard.controller;

import spring.flashcard.model.Card;
import spring.flashcard.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * The {@link CardController} class acts as a controller for handling HTTP requests related to flashcards.
 * It defines various endpoints for creating, retrieving, updating, and deleting flashcards.
 * This controller manages interactions with the {@link CardRepository} and responds to client requests.
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    CardRepository cardRepository;

    Logger logger = Logger.getLogger(CardController.class.getSimpleName());

    /**
     * Retrieve a list of flashcards, optionally filtered by word.
     * Trigger: GET request to "/api/cards"
     *
     * @param word Optional word filter.
     * @return ResponseEntity containing a list of matching flashcards or NO_CONTENT if empty.
     */
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

    /**
     * Retrieve a single flashcard by its unique ID.
     *
     * @param id The unique ID of the flashcard.
     * @return ResponseEntity containing the flashcard with the specified ID or NOT_FOUND if not found.
     * Trigger: GET request to "/api/cards/{id}"
     */
    @GetMapping("/cards/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id") long id) {
        Optional<Card> cardData = cardRepository.findById(id);

        return cardData.map(card -> new ResponseEntity<>(card, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Create a new flashcard.
     *
     * @param card The flashcard object to be created.
     * @return ResponseEntity containing the created flashcard with status CREATED or INTERNAL_SERVER_ERROR if an error occurs.
     * Trigger: POST request to "/api/cards"
     */
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

    /**
     * Update an existing flashcard by its ID.
     *
     * @param id   The unique ID of the flashcard to be updated.
     * @param card The updated flashcard object.
     * @return ResponseEntity containing the updated flashcard with status OK or NOT_FOUND if the flashcard does not exist.
     * Trigger: PUT request to "/api/cards/{id}"
     */
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

    /**
     * Delete a flashcard by its unique ID.
     *
     * @param id The unique ID of the flashcard to be deleted.
     * @return ResponseEntity with NO_CONTENT if deleted successfully, or INTERNAL_SERVER_ERROR if an error occurs.
     * Trigger: DELETE request to "/api/cards/{id}"
     */
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


    /**
     * Delete all flashcards.
     *
     * @return ResponseEntity with NO_CONTENT if all flashcards are deleted successfully, or INTERNAL_SERVER_ERROR if an error occurs.
     * Trigger: DELETE request to "/api/cards"
     */
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
