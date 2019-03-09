package lesson18.hw2;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.findNumbers("this text 125 about 126 people 12")));

        System.out.println(Arrays.toString(solution.findNumbers("this text about people ")));
    }
}
