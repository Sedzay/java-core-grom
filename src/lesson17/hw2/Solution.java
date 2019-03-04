package lesson17.hw2;

import java.util.Arrays;

public class Solution {

    public int[] findNumbers(String text) {
        if (text == null)
            return null;
        int[] arrInt = new int[0];
        String[] strings = text.split(" ");

        int number;
        for (String string : strings) {
            char[] chars = string.toCharArray();
            if (!chechNumbers(chars))
                System.out.println("not a number");
            else {
                number = Integer.parseInt(string);
                arrInt = Arrays.copyOf(arrInt, arrInt.length + 1);
                arrInt[arrInt.length - 1] = number;
            }
        }
        return arrInt;
    }

    private boolean chechNumbers(char[] chars) {
        if (chars == null)
            return false;
        for (char ch : chars) {
            if (!(48 <= ch && ch <= 57))
                return false;
        }
        return true;
    }
}

