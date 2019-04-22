package gromecode.lesson31.hw;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public Map<Character, Integer> countSymbols(String text) {
        Map<Character, Integer> mapCountChar = new HashMap<>();

        char[] chars = text.toCharArray();

        for (char ch : chars) {
            if (!Character.isLetter(ch))
                continue;
            if (mapCountChar.containsKey(ch)) {
                mapCountChar.put(ch, mapCountChar.get(ch) + 1);
            } else mapCountChar.put(ch, 1);
        }
        return mapCountChar;
    }


    public Map<String, Integer> words(String text) {
        Map<String, Integer> mapCountWords = new HashMap<>();

        String[] words = text.split(" ");

        for (String word : words) {
            if (!isWord(word))
                continue;
            if (mapCountWords.containsKey(word)) {
                mapCountWords.put(word, mapCountWords.get(word) + 1);
            } else mapCountWords.put(word, 1);
        }
        return mapCountWords;
    }


    private boolean isWord(String word) {

        char[] chars = word.toCharArray();

        if (chars.length <= 1)
            return false;

        for (char ch : chars) {
            if (!Character.isLetter(ch)) {
                return false;
            }
        }

        return true;
    }
}
