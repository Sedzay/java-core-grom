package lesson10.homeWork;

import java.util.Date;

public class FurnitureOrder extends Order {
    private String furnitureCode;

    public FurnitureOrder(String itemName, Date dateCreated, String shipFromCity, String shipToCity, int basePrice, Customer customerOwned, String furnitureCode) {
        super(itemName, dateCreated, shipFromCity, shipToCity, basePrice, customerOwned);
        this.furnitureCode = furnitureCode;
    }


    public String getFurnitureCode() {
        return furnitureCode;
    }

    @Override
    public void validateOrder() {
        if (((getShipFromCity() == "Киев")
                || (getShipFromCity() == "Львов"))
                && (getBasePrice() >= 500)
                && getCustomerOwned()!=null
                && (getCustomerOwned().getName() != "Тест")) {
            setDateConfirmed(new Date());
        }
    }

    @Override
    public void calculatePrice() {
        double deliveryPrice1 = 0.05;
        double deliveryPrice2 = 0.02;

        if (getBasePrice() < 5000) {
            setTotalPrice(getBasePrice() + getBasePrice() * deliveryPrice1);
        } else {
            setTotalPrice(getBasePrice() + getBasePrice() * deliveryPrice2);
        }
    }
}


