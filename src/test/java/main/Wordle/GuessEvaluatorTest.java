package main.Wordle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the method in GuessEvaluator class
 */
class GuessEvaluatorTest {

    /**
     * Instance variable of the Guess Evaluator method
     */
    private GuessEvaluator guesser = new GuessEvaluator();

    /**
     * A simple test for the setSecretWord method
     */
    @Test
    void setSecretWord() {
        assertEquals(guesser.getSecrectWord(), null);
        guesser.setSecretWord("hello");
        assertEquals(guesser.getSecrectWord(), "hello");
    }

    /**
     * A simple test for the AnalyzeGuess method
     */
    @Test
    void analyzeGuess() {
        guesser.setSecretWord("house");
        String return_string = guesser.analyzeGuess("horse");
        assertTrue(return_string.equals("**-**"));
        return_string = guesser.analyzeGuess("shoal");
        assertTrue(return_string.equals("+++--"));
        return_string = guesser.analyzeGuess("muddy");
        assertTrue(return_string.equals("-+---"));
        return_string = guesser.analyzeGuess("house");
        assertTrue(return_string.equals("*****"));
        return_string = guesser.analyzeGuess("drain");
        assertTrue(return_string.equals("-----"));


    }

    /**
     * A simple test for the validGuess method
     */
    @Test
    void validGuess() {
        assertFalse(guesser.validGuess("10wor"));
        assertTrue(guesser.validGuess("hello"));
        assertFalse(guesser.validGuess("apples"));

    }
}