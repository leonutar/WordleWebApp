package edu.virginia.sde.hw2.wordle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    private Dictionary dictionary;
    @BeforeEach
    void setUp() {dictionary = new Dictionary();}
    @Test
    void getWordSet() {
        var startingWordSet = new HashSet<>(Set.of("apple", "black", "camel"));
        var dictionary = new Dictionary(startingWordSet, new WordValidator());

        var wordSet = dictionary.getWordSet();
        assertEquals(3, wordSet.size());
        assertTrue(wordSet.contains("apple"));
        assertTrue(wordSet.contains("black"));
        assertTrue(wordSet.contains("camel"));
    }

    @Test
    void getWordSet_initiallyEmpty() {
        var dictionary = new Dictionary();

        var wordSet = dictionary.getWordSet();
        assertTrue(wordSet.isEmpty());
    }

    @Test
    void addWord_validWord() {
        dictionary.addWord("lions");
        assertEquals(1, dictionary.size());
        assertTrue(dictionary.contains("lions"));
    }

    @Test
    void addWord_invalidWord() {
        assertThrows(IllegalArgumentException.class, () -> {dictionary.addWord("apples");});
    }

    @Test
    void addWord_upperCase() {
        dictionary.addWord("LIONS");
        assertEquals(1, dictionary.size());
        assertTrue(dictionary.contains("lions"));
    }
    @Test
    void contains_Null() {
        assertFalse(dictionary.contains(null));
    }
    @Test
    void contains_weirdCases() {
        dictionary.addWord("lions");
        assertTrue(dictionary.contains("LIONS"));
        assertTrue(dictionary.contains("LiOnS"));
    }
    @Test
    void contains_emptyString() {
        assertFalse(dictionary.contains(""));
    }
    @Test
    void size_multipleAdds() {
        dictionary.addWord("lions");
        dictionary.addWord("hound");
        assertEquals(2, dictionary.size());
    }
    @Test
    void size_nullSet() {
        assertThrows(IllegalArgumentException.class, () -> {new Dictionary(null, new WordValidator());});
    }

}