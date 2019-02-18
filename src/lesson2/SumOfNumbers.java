package lesson2;

public class SumOfNumbers {
    public static void main(String[] args) {
        int i = 0;
        long res = 0;
        while (i<=10000000) {
            res += i;
            i++;
        }
        System.out.println(res);
    }
}
