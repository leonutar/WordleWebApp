package edu.virginia.sde.hw2.wordle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static edu.virginia.sde.hw2.wordle.LetterResult.*;
import static org.junit.jupiter.api.Assertions.*;

public class guessResultTest {
    @Test
    public void basicMatchTest() {
        GuessResult result = new GuessResult("brain", "basic");
        LetterResult[] expected = {GREEN, GRAY, YELLOW, GREEN, GRAY};
        assertArrayEquals(expected, result.getLetterResults());
    }
    @Test
    public void allGreenMatchTest() {
        GuessResult result = new GuessResult("lions", "lions");
        LetterResult[] expected = {GREEN, GREEN, GREEN, GREEN, GREEN};
        assertArrayEquals(expected, result.getLetterResults());
    }
    @Test
    public void noMatchTest() {
        GuessResult result = new GuessResult("lions", "grain");
        LetterResult[] expected = {GRAY, YELLOW, GRAY, YELLOW, GRAY};
        assertArrayEquals(expected, result.getLetterResults());
    }
    @Test
    public void mixedTest() {
        GuessResult result = new GuessResult("asset", "seats");
        LetterResult[] expected = {YELLOW, YELLOW, YELLOW, YELLOW, YELLOW};
        assertArrayEquals(expected, result.getLetterResults());
    }
    @Test
    public void oneYoneGreenDoubleTest() {
        GuessResult result = new GuessResult("apple", "papal");
        LetterResult[] expected = {YELLOW, YELLOW, GREEN, YELLOW, GRAY};
        assertArrayEquals(expected, result.getLetterResults());
    }
    @Test
    public void oneGreenOneGrayDoubleTest() {
        GuessResult result = new GuessResult("roost", "coach");
        LetterResult[] expected = {GRAY, GREEN, GRAY, GRAY, GRAY};
        assertArrayEquals(expected, result.getLetterResults());
    }

    @Test
    public void caseInsensitiveTest() {
        GuessResult result = new GuessResult("lIoNS", "LIOns");
        LetterResult[] expected = {GREEN, GREEN, GREEN, GREEN, GREEN};
        assertArrayEquals(expected, result.getLetterResults());
    }

    @Test
    public void incorrectLengthInGetLetterResultsTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new GuessResult("lion", "lions");});

        String expectedMessage = "Words must be 5 letters long";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage),
                "message does not contain expected content.");
    }
    @Test
    public void guessTooLongInGetLetterResultsTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GuessResult("lioness", "lions");
        });
    }
    @Test
    public void answerTooShortInGetLetterResultsTest() {
        assertThrows(IllegalArgumentException.class, () -> new GuessResult("lions", "lion"));
    }


    @Test
    public void repeatedEndLettersTest() {
        GuessResult result = new GuessResult("troll", "rolls");
        LetterResult[] expected = {GRAY, YELLOW, YELLOW, GREEN, YELLOW};
        assertArrayEquals(expected, result.getLetterResults());

    }

    @Test
    public void threeSameLettersTest() {
        GuessResult result = new GuessResult("pppaa", "apple");
        LetterResult[] expected = {GRAY, GREEN, GREEN, YELLOW, GRAY};
        assertArrayEquals(expected, result.getLetterResults());
    }

    @Test
    public void isCorrectTest_caseInsensitive() {
        GuessResult sameWordResult = new GuessResult("brain", "brain");
        assertTrue(sameWordResult.isCorrect(), "Expected 'brain' to be a correct guess for 'brain'");

        GuessResult caseInsensitiveResult = new GuessResult("BRAIN", "brain");
        assertTrue(caseInsensitiveResult.isCorrect(), "Expected 'BRAIN' to be a correct guess for 'brain'");
    }

    @Test
    public void isCorrect_diffWord(){
        GuessResult differentWordResult = new GuessResult("brain", "basic");
        assertFalse(differentWordResult.isCorrect(), "Expected 'brain' to be an incorrect guess for 'basic'");
    }


}
