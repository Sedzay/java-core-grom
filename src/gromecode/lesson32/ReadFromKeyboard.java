package gromecode.lesson32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadFromKeyboard {

    public static void main(String[] args) throws IOException{

        readKeyboardWithIOStream();

        readKeyboardWithScanner();
    }

    private static void readKeyboardWithScanner() {
        //using scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your name");

        String text = scanner.nextLine();
        if (!validate(text)) {
            System.out.println("Incorrect name!");
            return;
        }

        System.out.println("Hello, " + text + "!");

        scanner.close();
    }

    private static void readKeyboardWithIOStream() throws IOException {

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        System.out.println("Please enter your name");

        String text = br.readLine();
        if (!validate(text)) {
            System.out.println("Incorrect name!");
            return;
        } else
            System.out.println("Hello, " + text + "!");


    }

    private static boolean validate(String text) {
        char[] chars = text.toCharArray();
        for (char ch : chars) {
            if (!Character.isLetter(ch) || ch == ' ')
                return false;
        }
        return true;
    }
}
