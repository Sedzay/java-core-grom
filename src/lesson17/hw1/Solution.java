package lesson17.hw1;

import java.util.Arrays;

public class Solution {
    public int[] findNumbers(String text) {
        if (text == null)
            return null;
        int[] arrInt = new int[0];
        String[] strings = text.split(" ");

        int number;
        for (String string : strings) {
            try {
                number = Integer.parseInt(string);
                arrInt = Arrays.copyOf(arrInt, arrInt.length + 1);
                arrInt[arrInt.length - 1] = number;
            } catch (Exception e) {
                System.out.println("not a number");
            }
        }
        return arrInt;
    }
}
