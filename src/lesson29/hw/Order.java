package lesson29.hw;

import java.util.Objects;

public class Order {
    private long id;
    private int price;
    private String currency;
    private String itemName;
    private String shopIdentificator;

    public Order(long id, int price, String currency, String itemName, String shopIdentificator) {
        this.id = id;
        this.price = price;
        this.currency = currency;
        this.itemName = itemName;
        this.shopIdentificator = shopIdentificator;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return price == order.price &&
                currency.equals(order.currency) &&
                itemName.equals(order.itemName) &&
                shopIdentificator.equals(order.shopIdentificator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, currency, itemName, shopIdentificator);
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
