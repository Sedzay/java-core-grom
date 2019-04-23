package gromecode.lesson32.hw;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    public void readNumbers() throws Exception {
        int countAttempts = 3;
        int res = 0;

        while (countAttempts != 0 && res == 0){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Please enter numbers");

            String text = br.readLine();
            String[] numbers = text.split(" ");

            for (String st : numbers) {
                if (!validator(numbers)) {
                    countAttempts--;
                    if (countAttempts == 0) {
                        System.out.println("Your numbers are wrong. Number of attempts exceeded");
                        return;
                    }
                    System.out.println("Your numbers are wrong. You have " + countAttempts + " attempts to try again");
                    res = 0;
                    break;
                }
                res += stringToNumber(st);
            }
        }
        System.out.println("Your result is " + res);
    }

    private boolean validator(String[] stringNumbers) {
        if (stringNumbers.length != 10)
            return false;
        for (String num : stringNumbers) {
            if (stringToNumber(num) > 100)
                return false;
        }
        return true;
    }

    private int stringToNumber(String string) {
        for (char ch : string.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return 101;
            }
        }
        return Integer.parseInt(string);
    }
}
