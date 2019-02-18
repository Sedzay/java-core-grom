package lesson2;

public class SumAndDivision {
    public static void main(String[] args) {
        int i = 0;
        int res = 0;
        while (i < 1000) {
            res += i;
            i++;
        }
        int a = res / 1234;
        int b = res % 1234;
        boolean trueOrFalse = b > a;
        System.out.println(trueOrFalse);
    }
}
