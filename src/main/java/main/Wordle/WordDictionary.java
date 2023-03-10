/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Connor Vucovich
 * Section: 01 - 10a
 * Date: 3/25/22
 * Time: 10:36 AM
 *
 * Project: csci_205_hw
 * Package: main.Wordle
 * Class: WordDictionary
 *
 * Description: This method takes the existing word_list of items and creates a words.txt file and offers
 * the ability to check whether a word is located within that file or select a word from among that file
 *
 * ****************************************
 */
package main.Wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * A simple class to generate a word text file and select words for use in the wordle game
 */
public class WordDictionary {

    /**
     * The instance of Textprocessor to be used in the class
     */
    private TextProcessor wordList;

    /**
     * An arrayList object of all of the used words
     */
    private ArrayList<String> usedWords;

    /**
     * The constructor class for the WordDictionary object that initializes a word.txt object
     * @throws IOException - ensures that a valid URL is inputted
     */
    public WordDictionary() throws IOException {
        wordList = new TextProcessor();
        usedWords = new ArrayList<>();
    }

    /**
     * Generates the word.txt file with all of the words from the
     * @throws IOException - exception for invalid URLs inputted for the reader object
     */
    public void generateWordsTxt() throws IOException {
        Files.write(Paths.get("words.txt"), wordList.getWord_list().keySet());
    }

    /**
     * Generates the usedWord.txt file for all of the used already used within the Wordle game
     * @throws IOException - exception for invalid URLs inputted for the reader object
     */
    public void generateUsedWordsTxt() throws IOException {
        Files.write(Paths.get("usedWords.txt"), usedWords);
    }


    /**
     * This method take a given word from the list of valid words and ensures that it has not already been selected
     * @return String - the string of the word to be returned
     */
    public String selectWord() {
        int max = wordList.getWord_list().keySet().size();
        int min = 0;
        int range = max -min + 1;
        String return_word = "";
        int rand = (int)(Math.random() * range) + min;
        return_word = (String) wordList.getWord_list().keySet().toArray()[rand];
        while (usedWords.contains(return_word) == true) {
            rand = (int)(Math.random() * range) + min;
            return_word = (String) wordList.getWord_list().keySet().toArray()[rand];
        }
        usedWords.add(return_word);
        return return_word;
    }

    /**
     * Method that checks whether the inputted String by the user is contained in the list of valid words
     * @param inputWord - String representation of the word inputted by the user
     * @return boolean of whether the word is contained in the set or not contained
     */
    public boolean isWordInSet(String inputWord) {
        if (wordList.getWord_list().keySet().contains(inputWord)) {
            return true;
        }
        return false;
    }

    public TextProcessor getWordList() {
        return wordList;
    }

    public ArrayList<String> getUsedWords() {
        return usedWords;
    }
}