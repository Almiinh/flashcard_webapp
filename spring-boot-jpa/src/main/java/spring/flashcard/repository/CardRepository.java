package spring.flashcard.repository;

import spring.flashcard.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByCategoryContaining(String category);

    List<Card> findByWordContaining(String word);
}
