package lesson32.hw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    public void readNumbers() throws Exception {
        int countAttempts = 3;
        int res;

        do {
            res = 0;
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(inputStreamReader);

            System.out.println("Please enter numbers");

            String text = br.readLine();
            String[] numbers = text.split(" ");

            for (String st : numbers) {
                countAttempts--;
                if (numbers.length != 10 || stringToNumber(st) == 999) {
                    messageAboveMistake(countAttempts);
                    res = 999;
                    break;
                }
                res += stringToNumber(st);
            }
            if (res != 999) {
                System.out.println("Your result is " + res);
                return;
            }
        } while (countAttempts != 0);
    }

    private int stringToNumber(String string) {
        char[] chars = string.toCharArray();
        for (char ch : chars) {
            if (!Character.isDigit(ch)) {
                return 999;
            }
        }
        int number = Integer.parseInt(string);
        if (0 > number || number > 99) {
            return 999;
        }
        return number;
    }

    private void messageAboveMistake(int countAttempts) {
        if (countAttempts != 0) {
            System.out.println("Your numbers are wrong. You have " + countAttempts + " attempts to try again");
            return;
        }
        System.out.println("Your numbers are wrong. Number of attempts exceeded");
    }


}
