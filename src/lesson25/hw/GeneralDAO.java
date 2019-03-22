package lesson25.hw;

import java.util.Arrays;

public class GeneralDAO <T> {
    private T[] array = (T[])new Object[10];

    public T save(T t) throws Exception {

        int index = 0;
        for (T el : array) {
            if (el != null && el.equals(t))
                throw new Exception("Object with id " + t.toString() +  " already exist");
            if(el == null) {
                array[index] = t;
                return array[index];
            }
            index++;
        }
        throw new Exception("Object with id " + t.toString() + " can't saved, array is full");
    }

    public T[] getAll() {
        return array;
    }

}
