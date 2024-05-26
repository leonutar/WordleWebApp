package edu.virginia.sde.hw2.wordle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class WordValidatorTest {
    private static WordValidator wordValidator;
    @BeforeAll
    public static void initialize() {
        wordValidator = new WordValidator();
    }
    @Test
    public void isValidWord_false_tooLong() {
        assertFalse(wordValidator.isValidWord("DOGGIE"));
    }
    @Test
    public void isValidWord_false_tooShort() {
        assertFalse(wordValidator.isValidWord("DOG"));
    }

    @Test
    void isCorrectLength_True() {
        String word = "lions";
        assertTrue(wordValidator.isCorrectLength(word));
    }
    @Test
    void isCorrectLength_False() {
        String word = "lion";
        assertFalse(wordValidator.isCorrectLength(word));
    }
    @Test
    void isCorrectLength_EmptyString(){
        String word = "";
        assertFalse(wordValidator.isCorrectLength(word));
    }
    @Test
    void isAllLetters_True(){
        String word = "lions";
        assertTrue(wordValidator.isAllLetters(word));
    }

    @Test
    void isAllLetters_False(){
        String word = "li0ns";
        assertFalse(wordValidator.isAllLetters(word));
    }
    @Test
    void isAllLetters_LowerCase(){
        String word = "LIONs";
        assertTrue(wordValidator.isAllLetters(word));
    }

    @Test
    void isAllLetters_emptyString(){
        String word = "";
        assertFalse(wordValidator.isAllLetters(word));
    }
    @Test
    void isAllLetters_WithSpace(){
        String word = "li on";
        assertFalse(wordValidator.isAllLetters(word));
    }
    @Test
    void isAllLetters_Symbols(){
        String word = "lion!";
        assertFalse(wordValidator.isAllLetters(word));
    }


}
