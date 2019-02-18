package lesson4;

public class CompareSums {
    public static void main(String[] args) {
        System.out.println(compareSums( 1,2, 3,4));
    }

    public static boolean compareSums(int a, int b, int c, int d) {
        return sum(a, b) > sum(c, d) ? true : false;
    }

    public static long sum(int from, int to) {
        long sum = 0;
        for (long i = from; i <= to; i++) {
            sum += i;
        }
        System.out.println(sum);
        return sum;
    }
}
