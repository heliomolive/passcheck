package hmo.passcheck.service.rule.impl;

public class AsciiCharsCounter {

    public static int countOccurrences(char[] chars, int firstAscii, int lastAscii) {

        int occurrences = 0;
        int index = 0;

        while (index<chars.length) {
            int ascii = chars[index];
            if (ascii>=firstAscii && ascii<=lastAscii) {
                occurrences++;
            }
            index++;
        }
        return occurrences;
    }

    public static int countOccurrences(char[] chars, char c, int startIndex) {

        int occurrences = 0;
        for (int i=startIndex; i<chars.length; i++) {
            if (chars[i]==c) {
                occurrences++;
            }
        }
        return occurrences;
    }
}
