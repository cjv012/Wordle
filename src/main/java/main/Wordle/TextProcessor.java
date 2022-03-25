/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Connor Vucovich
 * Section: 01 - 10a
 * Date: 3/23/22
 * Time: 10:24 AM
 *
 * Project: csci_205_hw
 * Package: main.Wordle
 * Class: TextProcessor
 *
 * Description:
 *
 * ****************************************
 */
package main.Wordle;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * A class to read in all of the words to be used by wordle and then store them
 * into text file
 */
public class TextProcessor {
    /**
     * Map of all of the words in the array
     */
    private Map<String, Integer> word_list;

    /**
     * Integer object to count the total number of words
     */
    private Integer totalWords;

    /**
     * Integer object to count the total number of acceptable words
     */
    private Integer totalGoodWords;

    /**
     * Integer object to count the total number of unique words
     */
    private Integer totalUniqueWords;

    /**
     * A constructor method for the TextProcessor class that initializes the
     * read in information for the object
     */
    public TextProcessor() throws IOException {
        word_list = new HashMap<String, Integer>();
        totalWords = 0;
        totalGoodWords = 0;
        totalUniqueWords = 0;
        System.out.printf("-Reading in Pride and Prejudice by Jane Austen......");
        URLReader("https://www.gutenberg.org/files/1342/1342-0.txt");
        System.out.printf("done\n");
        System.out.printf("-Reading in Dracula by Bram Stoker......");
        URLReader("https://www.gutenberg.org/files/345/345-0.txt");
        System.out.printf("done\n");
        System.out.printf("-Reading in Moby Dick by Herman Melville......");
        URLReader("https://www.gutenberg.org/cache/epub/64317/pg64317.txt");
        System.out.printf("done\n");
        System.out.printf("-Reading in The Great Gatsby by Scott F. Fitzgerald......");
        URLReader("https://www.gutenberg.org/files/2701/2701-0.txt");
        System.out.printf("done\n");
        print_report();

    }

    /**
     * Method that takes in a URL outputs the words of size 5 to the words.txt file
     * @param URLString - the string representation of the URL to be used
     * @throws IOException - exception class thrown for invalid URLs
     */
    private void URLReader(String URLString) throws IOException {
        String address = URLString;
        URL locator = new URL(address);
        BufferedInputStream inStream= new BufferedInputStream(locator.openStream());
        Scanner scnr = new Scanner(inStream);
        String word = scnr.next();
        while(true == scnr.hasNext()) {
            word = scnr.next();
            word = word.replaceAll("[”“'',.?!;()]","");
            totalWords += 1;
            if (word.length() == 5 && word.matches("[a-z]+") && word_list.containsKey(word) == false) {
                totalGoodWords += 1;
                totalUniqueWords += 1;
                word_list.put(word, 1);
            } else if (word.length() == 5 && word.matches("[a-z]+") && word_list.containsKey(word) == true)  {
                word_list.put(word, word_list.get(word) + 1);
                totalGoodWords += 1;
            }
        }
    }

    /**
     * Prints out a report of the number of words added an discarded from the list as well as the most
     * popular items
     */
    public void print_report() {
        System.out.println("Total number of words processed: " + totalWords);
        System.out.println("Total number of words kept: " + totalGoodWords);
        System.out.println("Number of unique words: " + totalUniqueWords);
        System.out.println("Most frequently occurring word");
        String word_name = Collections.max(word_list.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey(); //borrowed from: https://stackoverflow.com/questions/5911174/finding-key-associated-with-max-value-in-a-java-map
        System.out.println(word_name + " : " + word_list.get(word_name));
    }
}