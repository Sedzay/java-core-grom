package gromecode.lesson17.hw3;

public class Solution {
    public static void main(String[] args) {

        String word = null;
        System.out.println(mostCountedWord(word));

        word = "not arr";
        System.out.println(mostCountedWord(word));

        word = "not arr not arr arr";
        System.out.println(mostCountedWord(word));

        word = "not    arr not arr arr";
        System.out.println(mostCountedWord(word));

        word = "not  11111 11111 11111  11111 arr not arr arr";
        System.out.println(mostCountedWord(word));

        word = "not  11111 11111 11111  11111 arr, not arr arr";
        System.out.println(mostCountedWord(word));

        word = "11111 11111 11111  11111 arr";
        System.out.println(mostCountedWord(word));
    }

    public static String mostCountedWord(String input) {
        if (input == null)
            return null;
        String[] strings = input.split(" ");
        String[] strings1 = input.split( " ");
        int[] countWords = new int[strings.length];

        int maxIndex = 0;

        for (int i = 0; i < strings.length; i++) {
            if (checkWord(strings[i])) {
                for (int j = 0; j < strings1.length; j++) {
                    if (strings[i].equals(strings1[j])) {
                        countWords[i]++;
                        strings1[j] = null;
                    }
                }
            }
            if(countWords[i] > countWords[maxIndex]) {
                maxIndex = i;
            }
        }
        boolean reiteration = false;
        for (int count : countWords) {
            if (count >= 1)
                reiteration = true;
        }
        if (!reiteration)
            return null;

        return strings[maxIndex];
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
