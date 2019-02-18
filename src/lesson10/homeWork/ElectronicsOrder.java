package lesson10.homeWork;

import java.util.Date;

public class ElectronicsOrder extends Order {
    private int guaranteeMonths;

    public ElectronicsOrder(String itemName, Date dateCreated, String shipFromCity, String shipToCity, int basePrice, Customer customerOwned, int guaranteeMonths) {
        super(itemName, dateCreated, shipFromCity, shipToCity, basePrice, customerOwned);
        this.guaranteeMonths = guaranteeMonths;
    }

    @Override
    public void validateOrder() {
        String[] cityName = {"Киев", "Одесса", "Днепр", "Харьков"};

        for (String cityFrom : cityName) {
            for (String cityTo : cityName) {
                if (getShipFromCity() == cityFrom
                        && getShipToCity() == cityTo
                        && getBasePrice() >= 100
                        && getCustomerOwned()!=null
                        && getCustomerOwned().getGender() == "Женский") {
                    setDateConfirmed(new Date());
                    break;
                }
            }
        }
    }

    @Override
    public void calculatePrice() {
        double deliveryPrice1 = 0.1;
        double deliveryPrice2 = 0.15;
        double sale = 0.05;

        double priceWithOutSale = getShipToCity() == "Киев" || getShipToCity() == "Одесса" ? getBasePrice() + getBasePrice() * deliveryPrice1 : getBasePrice() + getBasePrice() * deliveryPrice2;
        setTotalPrice(getBasePrice() > 1000 ? priceWithOutSale - priceWithOutSale * sale : priceWithOutSale);

        /*if(getShipToCity() == "Киев" || getShipToCity() == "Одесса") {
            priceWithOutSale = getBasePrice() + getBasePrice() * deliveryPrice1;
        }
        if(getShipToCity() == "Днепр" || getShipToCity() == "Харьков"){
            priceWithOutSale = getBasePrice() + getBasePrice() * deliveryPrice2;
        }*/

        /*if(getBasePrice() > 1000) {
            setTotalPrice(priceWithOutSale - priceWithOutSale * sale);
        }
        else {
            setTotalPrice(priceWithOutSale);
        }*/
    }
}
