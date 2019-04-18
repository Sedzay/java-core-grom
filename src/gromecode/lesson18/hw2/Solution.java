package gromecode.lesson18.hw2;

public class Solution {

    public int[] findNumbers(String text) {
        if (text == null)
            return null;

        String[] strings = text.split(" ");
        int count = 0;
        for (String string : strings) {
            if (chechNumbers(string.toCharArray()))
                count++;
        }

        int[] arrInt = new int[count];
        count = 0;
        for (String string : strings) {
            if (chechNumbers(string.toCharArray())) {
                arrInt[count] = Integer.parseInt(string);
                count++;
            }
            else {
                System.out.println("not a number");
            }
        }
        return arrInt;
    }

    private boolean chechNumbers(char[] chars) {
        if (chars == null)
            return false;
        for (char ch : chars) {
            if (!Character.isDigit(ch))
                return false;
        }
        return true;
    }
}

