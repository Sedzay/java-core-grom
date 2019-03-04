package lesson16.hw1;

public class Solution {
    public static void main(String[] args) {
        String word = "Аялот   It's    just test 1 or 3, 5 words, when i must to get 9 words лолот ";
        System.out.println(countWords(word));

    }

    public static int countWords(String input) {
        int count = 0;
        if (input == null)
            return count;
        String[] strings = input.split(" ");

        for (String string : strings) {
            if (string != "" && checkWord(string))
                count++;
        }
        return count;
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
}
