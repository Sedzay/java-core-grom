package lesson30.hw;

import java.util.Objects;

public class Project extends IdEntity {

    private long id;
    private String name;
    private Customer customer;

    public Project(long id, String name, Customer customer) {
        this.id = id;
        this.name = name;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return name.equals(project.name) &&
                customer.equals(project.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, customer);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", customer=" + customer +
                '}';
    }
}
