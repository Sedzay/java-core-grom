package lesson5;

import java.util.Arrays;

public class SortArray {
    public static void main(String[] args) {
        int[] array = {5, 10, 3, 6, 12, -3};
        System.out.println(Arrays.toString(sortAscending(array)));
        System.out.println(Arrays.toString(sortDescending(array)));
        System.out.println(0-2147483647);
    }

    static int[] sortAscending(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }

    static int[] sortDescending(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }
}
