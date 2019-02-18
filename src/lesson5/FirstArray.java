package lesson5;

import java.util.Arrays;

public class FirstArray {
    public static void main(String[] args) {
        int []firstArray = new int[3];
        System.out.println(Arrays.toString(firstArray));
        firstArray[0] = 1;
        firstArray[1] = 10;
        firstArray[2] = 25;

        System.out.println(Arrays.toString(firstArray));

        for (int el: firstArray) {
            System.out.print(el + ", ");
        }
    }
}
