package gromecode.lesson16;

import java.util.Arrays;

public class StringCompare {
    public static void main(String[] args) {

        System.out.println(new String("abc") == new String("abc"));
        System.out.println(new String("abc").equals(new String("abc")));
        System.out.println("abs" == "abs");
        System.out.println("Abs".equals("abs"));

        String s1 = "test";
        String s2 = "test";
        System.out.println(s1 == s2);

        //using intern

        String s3 = new String("pppp");
        String s4 = "pppp";
        System.out.println(s3 == s4);

        s3 = s3.intern();
        System.out.println(s3 == s4);


        //bytes of string

        String str = "testStringVar";
        System.out.println(Arrays.toString(str.getBytes()));

        System.out.println(new String(str.getBytes()));
    }
}
