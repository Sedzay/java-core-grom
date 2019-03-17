package lesson22.arrays;

public class ArrayUtils {

    public static int maxElement (int[] array) {
        int max = array [0];

        for (int el: array) {
            if (el > max) {
                max = el;
            }
        }
        return max;
    }
    public static int nCount (int[] array, int n) {
        int count = 0;
        for (int el: array) {
            if (el == n) {
                count++;
            }
        }
        return count;
    }

    public static int[] sortAscending(int[] array) {
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

    public static int[] sortDescending(int[] array) {
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
