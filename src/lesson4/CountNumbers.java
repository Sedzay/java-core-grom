package lesson4;

public class CountNumbers {
    public static void main(String[] args) {
        short a = 1;
        short b = 20;
        System.out.println(findDivCount(a, b, 3));
    }

    public static int findDivCount(short a, short b, int n) {
        int count = 0;
        int x = (int)a;
        int y = (int)b;
        for (int i = x; i<=y; i++) {
            if (i % n == 0) {
                count++;
            }
        }
        return count;
    }
}
