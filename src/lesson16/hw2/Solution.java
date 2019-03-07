package lesson16.hw2;

public class Solution {
    public static void main(String[] args) {
        String word = "Аялот   It's    just test 1 or 3, 5 wordsnlnk when i must to get 9 words лолот ";
        System.out.println(maxWord(word));
        System.out.println(minWord(word));
    }
    //самое длинное и самое короткое слово

    public static String maxWord(String input) {
        if (input == null)
            return null;
        String[] strings = input.split(" ");
        String maxString = null;
        for (String string : strings) {
            if (checkWord(string)) {
                if (maxString == null || string.length() > maxString.length()) {
                    maxString = string;
                }
            }
        }
        return maxString;
    }

    public static String minWord(String input) {
        if (input == null)
            return null;
        String[] strings = input.split(" ");
        String minString = null;
        for (String string : strings) {
            if (checkWord(string)) {
                if (minString == null || string.length() < minString.length()) {
                    minString = string;
                }
            }
        }
        return minString;
    }

    private static boolean checkWord(String word) {
        char[] chars = word.toCharArray();

        for(char ch : chars) {
            if(Character.isLetter(ch)) {
                return true;
            }
        }
        return false;
    }
}
