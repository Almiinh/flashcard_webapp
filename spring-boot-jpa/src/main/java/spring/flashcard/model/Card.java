package spring.flashcard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The {@link Card} class represents a flashcard containing word, translation, and category information.
 * Flashcards are used for language learning and memorization.
 * <p>
 * Each card has a unique identifier ({@link #id}), a word to be learned, its translation, and a category
 * that groups related words together.
 */
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "word")
    private String word;

    @Column(name = "translation")
    private String translation;

    @Column(name = "category")
    private String category;

    public Card() {
    }

    /**
     * Constructs a new Card with the specified word, translation, and category.
     *
     * @param word        The word to be learned.
     * @param translation The translation of the word.
     * @param category    The category that groups related words together.
     */
    public Card(String word, String translation, String category) {
        this.word = word;
        this.translation = translation;
        this.category = category;
    }

    /**
     * Gets the unique identifier of the card.
     *
     * @return The unique identifier of the card.
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the word to be learned.
     *
     * @return The word to be learned.
     */
    public String getWord() {
        return word;
    }

    /**
     * Sets the word to be learned.
     *
     * @param word The word to be learned.
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Gets the translation of the word.
     *
     * @return The translation of the word.
     */
    public String getTranslation() {
        return translation;
    }

    /**
     * Sets the translation of the word.
     *
     * @param translation The translation of the word.
     */
    public void setTranslation(String translation) {
        this.translation = translation;
    }


    /**
     * Gets the category that groups related words together.
     *
     * @return The category of the card.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category that groups related words together.
     *
     * @param category The category of the card.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns a string representation of the card.
     *
     * @return A string representation of the card.
     */
    @Override
    public String toString() {
        return "Card [id=" + id + ", word=" + word + ", translation=" + translation + ", category=" + category + "]";
    }

}
