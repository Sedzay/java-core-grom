package lesson31.hw;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public Map<Character, Integer> countSymbols(String text) {
        Map<Character, Integer> mapCountChar = new HashMap<>();

        char[] chars = text.toCharArray();

        for (char ch : chars) {
            if (Character.isLetter(ch)) {
                int count = 0;
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == ch) {
                        count++;
                    }
                }
                mapCountChar.put(ch, count);
            }
        }
        return mapCountChar;
    }


    public Map<String, Integer> words(String text) {
        Map<String, Integer> mapCountWords = new HashMap<>();

        String[]words = text.split(" ");

        for (String word : words) {
            if (isWord(word)) {
                int count = 0;
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals(word)) {
                        count++;
                    }
                }
                mapCountWords.put(word, count);
            }
        }

        return mapCountWords;
    }


    private boolean isWord(String word) {

        char[]chars = word.toCharArray();

        if(chars.length <=1 )
            return false;

        for (char ch : chars) {
            if(!Character.isLetter(ch)) {
                return false;
            }
        }

        return true;
    }
}
