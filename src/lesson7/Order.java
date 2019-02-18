package lesson7;

import java.util.Date;

public class Order {
    long id;
    int price;
    Date dateCreated;
    boolean isConfirmed;
    Date dateConfirmed;
    String city;
    String country;
    String type;

    /*public static void main(String[] args) {
        System.out.println(new Date());
    }*/

    public Order(int price, Date dateCreated, boolean isConfirmed, Date dateConfirmed, String city, String country, String type) {
        this.price = price;
        this.dateCreated = dateCreated;
        this.isConfirmed = isConfirmed;
        this.dateConfirmed = dateConfirmed;
        this.city = city;
        this.country = country;
        this.type = type;
    }

    public Order() {
    }

    void confirmOrder() {
        if (!isConfirmed) {
            isConfirmed = true;
            dateConfirmed = new Date();
        }
    }

    boolean checkPrice() {
        return (price > 1000) ? true : false;
    }

    boolean isValidType() {
        return (type == "Buy" || type == "Sale") ? true : false;
    }
}
