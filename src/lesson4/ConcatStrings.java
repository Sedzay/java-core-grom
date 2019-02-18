package lesson4;

public class ConcatStrings {
    public static void main(String[] args) {
        System.out.println(concat("First text", ".....", "final text"));
    }

    public static String concat(String str1, String str2, String str3) {
        return str1 + str2 + str3;
    }
}
