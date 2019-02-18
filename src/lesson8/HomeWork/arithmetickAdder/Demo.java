package lesson8.HomeWork.arithmetickAdder;

public class Demo {
    public static void main(String[] args) {
        int[] array = {1,3,6,12,1000,500};
        int a = 2147483647;
        int b = 2147483647;

        Adder adder = new Adder();

        System.out.println(adder.check(array));
        System.out.println(adder.add(a,b));
    }
}
