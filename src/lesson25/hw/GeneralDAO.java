package lesson25.hw;

import java.util.Arrays;

public class GeneralDAO <T extends IdEntity> {
    private T[] array = (T[])new IdEntity[10];

    public T save(T t) throws Exception {
        if(t == null)
            return null;

        int index = 0;
        for (T el : array) {
            if (el != null && el.getId() == t.getId())
                throw new Exception("Object with id " + t.getId() +  " already exist");
            if(el == null) {
                array[index] = t;
                return array[index];
            }
            index++;
        }
        throw new Exception("Object with id " + t.getId() + " can't saved, array is full");
    }

    public T[] getAll() {
        return array;
    }

}
