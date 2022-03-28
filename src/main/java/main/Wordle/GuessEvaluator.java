/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Connor Vucovich
 * Section: 01 - 10a
 * Date: 3/28/22
 * Time: 3:27 AM
 *
 * Project: csci_205_hw
 * Package: main.Wordle
 * Class: GuessEvaluator
 *
 * Description:
 *
 * ****************************************
 */
package main.Wordle;

public class GuessEvaluator {

    /**
     * The String representation of the word to be guessed by the user
     */
    private String secrectWord;

    /**
     * The string representation of the guess by the user
     */
    private String currentGuess;

    /**
     * The string of "-+*" that represent whether the letters are correct or incorrect
     */
    private String guessAnalysis;

    /**
     * Setter method for secretWord
     * @param secretW - the word to be guessed by the user
     */
    public void setSecretWord(String secretW) {
        secrectWord = secretW;
    }

    /**
     * Analyzer method for the input String that returns a string of -+* that relate to
     * whether the guess was correct or incorrect
     * @param guess - String representation of the userd guess
     * @return - a String of -+* that represent whether the given character is correct or not
     */
    public String analyzeGuess(String guess) {
        guessAnalysis = "";
        for (int i = 0; i < 5; i++) {
            if (getSecrectWord().charAt(i) == guess.charAt(i)) {
                guessAnalysis += "*";
            } else if (getSecrectWord().contains((String.valueOf(guess.charAt(i)))) == true) {
                guessAnalysis += "+";
            } else {
                guessAnalysis += "-";
            }
        }
        return guessAnalysis;
    }

    public String getSecrectWord() {
        return secrectWord;
    }
}