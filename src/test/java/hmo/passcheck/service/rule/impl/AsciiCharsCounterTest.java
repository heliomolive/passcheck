package hmo.passcheck.service.rule.impl;

import org.junit.jupiter.api.Test;

import static hmo.passcheck.service.rule.impl.AsciiCharsCounter.countOccurrences;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsciiCharsCounterTest {

    private char[] charsArray = "asdfgaa01234566!::~~~".toCharArray();

    @Test
    public void countOccurrencesForCharsInterval() {
        int occurrences = countOccurrences(charsArray, (int)'a', 'z');

        assertEquals(7, occurrences);
    }

    @Test
    public void countOccurrencesForCharsIntervalWithDigits() {
        int occurrences = countOccurrences(charsArray, (int)'0', '9');

        assertEquals(8, occurrences);
    }

    @Test
    public void countOccurrencesForCharsIntervalWithinSpecialCharsGroup1() {
        int occurrences = countOccurrences(charsArray, (int)'!', '/');

        assertEquals(1, occurrences);
    }

    @Test
    public void countOccurrencesForCharsIntervalWithinSpecialCharsGroup2() {
        int occurrences = countOccurrences(charsArray, (int)':', (int)'@');

        assertEquals(2, occurrences);
    }

    @Test
    public void countOccurrencesForCharsIntervalWithinSpecialCharsGroup3() {
        int occurrences = countOccurrences(charsArray, (int)'{', (int)'~');

        assertEquals(3, occurrences);
    }

    @Test
    public void countOccurrencesForCharsIntervalNoOccurences() {
        int occurrences = countOccurrences("asdfg".toCharArray(), (int)'0', '9');

        assertEquals(0, occurrences);
    }

    @Test
    public void countOccurrencesForSpecificChar() {
        int occurrences = countOccurrences(charsArray, 'a', 0);

        assertEquals(3, occurrences);
    }

    @Test
    public void countOccurrencesForSpecificCharNoOccurrences() {
        int occurrences = countOccurrences(charsArray, 'a', 7);

        assertEquals(0, occurrences);
    }
}
