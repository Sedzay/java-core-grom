package lesson30.hw;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDAO <T extends IdEntity>{

    private HashSet<T> collection = new HashSet<>();

    public HashSet<T> getCollection() {
        return collection;
    }

    public T add(T t) {

        collection.add(t);
        System.out.println(t.getClass().getSimpleName() + " with id " + t.getId() + " added successfully!");
        return t;
    }

    public void remove(long id) {

        T t = null;
        for (T t2 : collection) {
            if (t2 != null && t2.getId() == id) {
                t = t2;
            }
        }

        if (t == null) {
            System.out.println("Error! " + t.getClass().getSimpleName() + " with id " + id + " does not exist!");
            return;
        }

        collection.remove(t);
        System.out.println(t.getClass().getSimpleName() + " with id " + t.getId() + " removed successfully!");
    }

    public T update(T t) {

        for (T t2 : collection) {
            if (t2 != null && t2.getId() == t.getId()) {
                remove(t.getId());
                add(t);
                return t;
            }
        }

        System.out.println("Error! " + t.getClass().getSimpleName() + " with id " + t.getId() + " does not exist!");
        return null;
    }

    @Override
    public abstract String toString();
}
