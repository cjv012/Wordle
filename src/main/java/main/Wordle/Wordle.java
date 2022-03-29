/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Connor Vucovich
 * Section: 01 - 10a
 * Date: 3/28/22
 * Time: 9:37 PM
 *
 * Project: csci_205_hw
 * Package: main.Wordle
 * Class: Wordle
 *
 * Description: This file encapsulates the functionality of the entire Wordle program by bringing together
 * all of the functionality of generating a list of viable words, creating a wordle game, and checking
 * guesses to see if they are correct
 *
 * ****************************************
 */
package main.Wordle;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Enumeration Class of the Game State of the Wordle game
 */
enum GameState {
    NEW_GAME,
    GAME_IN_PROCESS,
    GAME_WINNER,
    GAME_LOSER

}

/**
 * A simple class to encapsulate the interface of the Wordle Game
 */
public class Wordle {

    private GameState gameState;

    /**
     * The instance of the dictionary class for the Wordle Game
     */
    private WordDictionary item;

    /**
     * The instance of GuessEvaluator for the Wordle Game
     */
    private GuessEvaluator guesser;
    /**
     * Constructor for the Wordle Class that initializes all of the game functionality and then initializes a game
     */
    public Wordle() throws IOException {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to Wordle!");
        gameState = GameState.NEW_GAME;
        wordTextController(scnr);
        System.out.println("Done!");
        System.out.println("");
        guesser = new GuessEvaluator();
        initGame();
    }

    /**
     * Method takes in a scanner object and uses it to determine whether a words.txt file exists and whether the user
     * would like to generate another one. It prints out some of the details of the report as well
     * @param scnr - scanner object that takes in the user response
     * @throws IOException - thrown for invalid URL types
     */
    private void wordTextController(Scanner scnr) throws IOException {
        File tempFile = new File("words.txt");
        boolean exists = tempFile.exists();
        System.out.println("Reading in File Data!");
        item = new WordDictionary();
        if (exists != true) {
            System.out.println("NO DICTIONARY FOUND!");
            System.out.println("GENERATING words.txt...");
            item.generateWordsTxt();
            item.getWordList().print_report();
        } else {
            System.out.printf("Would you like to delete words.txt and regenerate it? [Y/N]: ");
            String deleteString = scnr.nextLine();
            if (deleteString.equals("Y")) {
                deleteWordsTxt();
                System.out.println("REGENERATING words.txt...");
                item.generateWordsTxt();
                item.getWordList().print_report();
            }
        }
        System.out.printf("Would you like to delete usedWords.txt? [Y/N]: ");
        String UsedDeleteString = scnr.nextLine();
        if (UsedDeleteString.equals("Y")) {
            deleteUsedWordsTxt();
        }

    }

    /**
     * Method initializes a game and then calls on the gameplay operator and the endGame operators to complete the construction
     */
    public void initGame() throws IOException {
        if (gameState == GameState.NEW_GAME) {
            String userGuess;
            String guessResult;
            System.out.println("Ready to play Wordle! You have 6 guesses!");
            gameState = GameState.GAME_IN_PROCESS;
            String wordSelect = item.selectWord();
            guesser.setSecretWord(wordSelect);
            Scanner scnr = new Scanner(System.in);
            gamePlayOperator(scnr);
            endOfGame(scnr);


        }
    }

    /**
     * Method takes in a scanner object and the loop through the 6 possible user responses
     * checking to see if they are correct and how related they are to the true response value
     * @param scnr - scanner object to take in the user response
     */
    private void gamePlayOperator(Scanner scnr) {
        String guessResult;
        String userGuess;
        for (int i = 1; i <= 6; i++) {
            System.out.printf("Guess %d: ", i);
            userGuess = scnr.nextLine().toLowerCase(Locale.ROOT);
            while (guesser.validGuess(userGuess) == false || item.isWordInSet(userGuess) == false) {
                System.out.println("Invalid Guess! Please Enter a Valid 5 Letter Word Using Alphabetical Letters Only!");
                System.out.printf("Guess %d: ", i);
                userGuess = scnr.nextLine().toLowerCase(Locale.ROOT);
            }
            guessResult = guesser.analyzeGuess(userGuess);
            if (!guessResult.equals("*****")) {
                System.out.println("   -->   " + guessResult + "   Try again. " + String.valueOf(6-i) + " guesses left.");
            } else {
                System.out.println("   -->   " + guessResult + "   YOU WON! You guessed the word in " + String.valueOf(i) + " turns.");
                gameState = GameState.GAME_WINNER;
                break;
            }
        }
    }

    /**
     * End of game operator that asks the user if they would like to play again and
     * may make a call to start another game depending on the response from the user
     * @param scnr - scanner object to take in the user's response
     */
    private void endOfGame(Scanner scnr) throws IOException {
        if (gameState == GameState.GAME_IN_PROCESS) {
            gameState = GameState.GAME_LOSER;
        }
        System.out.printf("Would you like to play again? [Y/N]: ");
        String playAgain = scnr.nextLine();
        gameState = GameState.NEW_GAME;
        item.generateUsedWordsTxt();
        if (playAgain.equals("Y") == true) {
            System.out.println("");
            initGame();
        } else {
            System.out.println("Goodbye!");
        }
    }

    /**
     * Method to delete the words.txt file
     */
    public void deleteWordsTxt() {
        String filename = "words.txt";
        File fileObject = new File(filename);
        fileObject.delete();
    }

    /**
     * Method to delete the usedWords.txt file
     */
    public void deleteUsedWordsTxt() {
        String filename = "usedWords.txt";
        File fileObject = new File(filename);
        fileObject.delete();
    }
}