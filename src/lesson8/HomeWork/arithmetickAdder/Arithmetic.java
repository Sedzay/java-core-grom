package lesson8.HomeWork.arithmetickAdder;

public class Arithmetic {
    boolean check (int[] array) {
        return array!=null&&((array[0] + array[array.length-1])>100) ? true : false;
    }
}
