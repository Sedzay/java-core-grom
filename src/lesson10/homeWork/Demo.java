package lesson10.homeWork;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {

        /*ElectronicsOrder electronicsOrder = new ElectronicsOrder("first",new Date(),"Киев", "Днепр",1200,new Customer("Anna","Днепр","Женский"),3);
        ElectronicsOrder electronicsOrder2 = new ElectronicsOrder("second",new Date(),"Одесса", "Харьков",500,new Customer("Jack","Харьков","Мужской"),3);

        electronicsOrder.validateOrder();
        electronicsOrder.calculatePrice();
        electronicsOrder.confirmShipping();

        electronicsOrder2.validateOrder();
        electronicsOrder2.calculatePrice();
        electronicsOrder2.confirmShipping();

        FurnitureOrder furnitureOrder = new FurnitureOrder("third",new Date(),"Одесса", "Харьков",5500,new Customer("Owen","Харьков","Мужской"),"codeOne");
        FurnitureOrder furnitureOrder2 = new FurnitureOrder("four",new Date(),"Припять", "Кулебовка",1000,new Customer("Sami","Харьков","Женский"),"codeTwo");

        furnitureOrder.validateOrder();
        furnitureOrder.calculatePrice();
        furnitureOrder.confirmShipping();

        furnitureOrder2.validateOrder();
        furnitureOrder2.calculatePrice();
        furnitureOrder2.confirmShipping();

        System.out.println(furnitureOrder2.getDateShipped());
        System.out.println(electronicsOrder.getTotalPrice());*/

        //-------------------------------------------------------------------
        //Если Customer null
        ElectronicsOrder electronicsOrder = new ElectronicsOrder("first",new Date(),"Киев", "Днепр",1200,null,3);

        electronicsOrder.validateOrder();
        electronicsOrder.calculatePrice();
        electronicsOrder.confirmShipping();

        //если цена отрицательная
        ElectronicsOrder electronicsOrder2 = new ElectronicsOrder("second",new Date(),"Киев", "Днепр",-1200,new Customer("Anna","Днепр","Женский"),3);

        electronicsOrder2.validateOrder();
        electronicsOrder2.calculatePrice();
        electronicsOrder2.confirmShipping();

        //если гарантийный срок отрицательный
        ElectronicsOrder electronicsOrder3 = new ElectronicsOrder("third",new Date(),"Киев", "Днепр",1200,new Customer("Anna","Днепр","Женский"),-3);

        electronicsOrder3.validateOrder();
        electronicsOrder3.calculatePrice();
        electronicsOrder3.confirmShipping();



    }
}