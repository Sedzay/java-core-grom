package gromecode.lesson27.hw;

import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListTest {

    public LinkedList<Order> useList() {
        LinkedList<Order> list = new LinkedList<>();
        //add
        list.add(new Order(10001, 100, "usd", "itemName1", "someId"));
        list.add(new Order(10002, 200, "ua", "itemName2", "someId"));
        System.out.println(list);

        //add by index
        Order order3 = new Order(10003, 555, "eur", "itemName3", "someId3");
        list.add(1, order3);
        System.out.println(list);

        //remove by index
        list.remove(0);
        System.out.println(list);

        //remove by Object
        list.remove(order3);
        System.out.println(list);

        //addAll
        LinkedList<Order> list2 = new LinkedList<>();
        Order order4 = new Order(10004, 12, "eur", "itemName5", "someId");
        Order order5 = new Order(10005, 2, "eur", "itemName", "someId");
        Order order6 = new Order(10006, 120, "eur", "itemName", "someId");
        Order order7 = new Order(10007, 10, "eur", "itemName", "someId");
        Order order8 = new Order(10008, 303, "eur", "itemName", "someId");
        Order order9 = new Order(10009, 15, "eur", "itemName", "someId");

        list2.add(order4);
        list2.add(order5);
        list2.add(order6);
        list2.add(order7);
        list2.add(order8);
        list2.add(order9);

        list.addAll(list2);
        System.out.println(list);
        System.out.println(list2);

        //subList
        System.out.println(list.subList(1, 3).contains(order5));
        System.out.println(list.subList(1, 3).contains(order9));
        list.subList(2, 4).clear();
        System.out.println(list);

        //set
        list.set(1, order5);
        list.set(0, list2.get(5));
        System.out.println(list);

        //contains
        System.out.println(list.contains(order3));
        System.out.println(list2.contains(order5));

        //Object[] toArray
        Order[] orders = new Order[list.size()];
        list.toArray(orders);
        System.out.println(Arrays.deepToString(orders));
        System.out.println(list);

        //clear
        list2.clear();
        System.out.println(list2);
        System.out.println(list);


        return list;
    }
}
