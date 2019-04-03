package lesson30.hw;

import java.util.Date;
import java.util.HashSet;

public class Firm {

    private Date dateFounder;
    private HashSet<Department> departments;
    private HashSet<Customer> customers;

    public Firm(Date dateFounder, HashSet<Department> departments, HashSet<Customer> customers) {
        this.dateFounder = dateFounder;
        this.departments = departments;
        this.customers = customers;
    }
}
