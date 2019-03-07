package lesson17.hw1;

public class Solution {
    public int[] findNumbers(String text) {
        if (text == null)
            return null;

        String[] strings = text.split(" ");
        int count = 0;
        for (String string : strings) {
            try {
                Integer.parseInt(string);
                count++;
            } catch (Exception e) {
            }
        }
        int[] arrInt = new int[count];
        count = 0;
        for (String string : strings) {
            try {
                arrInt[count] = Integer.parseInt(string);
                count++;
            } catch (Exception e) {
                System.out.println("not a number");
            }
        }
        return arrInt;
    }
}
