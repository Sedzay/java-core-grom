package lesson3;

public class FindOdd {
    public static void main(String[] args) {
        int i = 0;
        int res = 0;
        while (i <= 1000) {
            if (!(i % 2 == 0)) {
                System.out.println("Found");
                res += i;
            }
            i++;
        }
        System.out.println((res *=5)>5000 ? "Bigger" : "Smaller or equal");
    }
}
