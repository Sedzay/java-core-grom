package gromecode.lesson30.hw;

import java.util.HashSet;

public class CustomerDAO {

    private HashSet<Customer> collection = new HashSet<>();

    public HashSet<Customer> getCollection() {
        return collection;
    }

    public String toString() {
        return "CustomerDAO{" +
                "customers=" + collection +
                '}';
    }
}
