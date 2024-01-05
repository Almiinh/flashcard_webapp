package spring.flashcard.repository;

import spring.flashcard.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The {@link CardRepository} interface extends the {@link JpaRepository} interface and defines custom query methods
 * for accessing and managing flashcard data in the database. It allows for the retrieval and manipulation
 * of flashcards based on their category or word.
 */
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByCategoryContaining(String category);

    List<Card> findByWordContaining(String word);
}
