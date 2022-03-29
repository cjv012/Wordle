package main.Wordle;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the method in the WordDictionary class
 */
class WordDictionaryTest {

    /**
     * Stores a WordDictionary class item
     */
    private WordDictionary item;

    /**
     * Constructor class for a WordDictionary Item
     * @throws IOException - throws for a fualty inputted URL
     */
    WordDictionaryTest() throws IOException {
        item = new WordDictionary();
    }

    /**
     * A simple test for the selectWord method
     */
    @Test
    void selectWord() {
        String selectedWord = item.selectWord();
        boolean isInList = item.isWordInSet(selectedWord);
        assertTrue(isInList);
        assertTrue(selectedWord.length() == 5);
        assertTrue(selectedWord.matches("[a-z]+"));
    }

    @Test
    void isWordInSet() {
        assertTrue(item.isWordInSet("which"));
        assertTrue(item.isWordInSet(item.selectWord()));
        assertFalse(item.isWordInSet("the"));
        assertFalse(item.isWordInSet("cannot"));
        assertFalse(item.isWordInSet("Bryce"));
        assertFalse(item.isWordInSet("can't"));
        assertFalse(item.isWordInSet("(ten)"));
        assertFalse(item.isWordInSet("the1q"));
    }
}