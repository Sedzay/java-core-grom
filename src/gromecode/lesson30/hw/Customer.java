package gromecode.lesson30.hw;

import java.util.Objects;
import java.util.Random;

public class Customer {

    private long id;
    private String name;
    private String country;
    private int monthlyPay;

    public Customer(String name, String country, int monthlyPay) {

        Random random = new Random();
        this.id = random.nextInt();

        this.name = name;
        this.country = country;
        this.monthlyPay = monthlyPay;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getMonthlyPay() {
        return monthlyPay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name) &&
                country.equals(customer.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", monthlyPay=" + monthlyPay +
                '}';
    }
}
