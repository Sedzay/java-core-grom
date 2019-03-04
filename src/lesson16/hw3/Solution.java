package lesson16.hw3;

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

        for (int i = 0; i < strings.length; i++) {
            if (checkWord(strings[i])) {
                for (int j = 0; j < strings1.length; j++) {
                    if (strings[i].equals(strings1[j])) {
                        countWords[i]++;
                        strings1[j] = null;
                    }
                }
            }
        }
        if (!checkReiteration(countWords))
            return null;
        return strings[maxCounted(countWords)];
    }

    private static boolean checkWord(String word) {
        char[] chars = word.toCharArray();

        for(char ch : chars) {
            if(((65 <= ch && ch <= 90) || (97 <= ch && ch <= 122) || (1040 <= ch && ch <= 1103))) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkReiteration(int[] arr) {
        for (int count : arr) {
            if (count >= 1)
                return true;
        }
        return false;
    }

    private static int maxCounted(int[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
