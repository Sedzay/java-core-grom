package lesson29.hw;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetTest {

    public Set<Order> useHashSet() {

        Set<Order> orders = new HashSet<>();

        Order order1 = new Order(1001, 100, "USD", "name1", "id1");
        Order order2 = new Order(1002, 200, "USD", "name2", "id2");
        Order order3 = new Order(1003, 300, "USD", "name3", "id3");
        Order order4 = new Order(1004, 400, "USD", "name4", "id4");
        Order order5 = new Order(1005, 500, "USD", "name5", "id5");
        Order order6 = new Order(1006, 600, "USD", "name6", "id6");
        Order order7 = new Order(1007, 700, "USD", "name7", "id7");
        Order order8 = new Order(1008, 800, "USD", "name8", "id8");

        //add
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);
        orders.add(order7);
        orders.add(order8);

        System.out.println(orders);
        System.out.println();

        Iterator<Order> orderIterator = orders.iterator();
        while (orderIterator.hasNext()) {
            System.out.println(orderIterator.next().getId());
        }

        //remove
        orders.remove(order1);

        System.out.println();
        System.out.println(orders);

        //retailAll
        Set<Order> orders2 = new HashSet<>();

        orders2.add(order2);
        orders2.add(order3);
        orders2.add(order4);
        orders2.add(order5);
        orders2.add(order7);

        orders.retainAll(orders2);

        System.out.println();
        System.out.println(orders);

        Iterator<Order> orderIterator2 = orders.iterator();
        while (orderIterator2.hasNext()) {
            System.out.println(orderIterator2.next().getId());
        }

        //Object[]to Array
        Order[]ordersArray = orders2.toArray(new Order[0]);

        System.out.println();

        for (Order order : ordersArray) {
            System.out.println(order);
        }

        //size
        System.out.println(orders.size());

        //вторая проверка retain, такой же объект по equals, разный id,
        Set<Order> orders3 = new HashSet<>();
        Order order9 = new Order(100, 700, "USD", "name7", "id7");
        orders3.add(order9);
        orders2.retainAll(orders3);

        System.out.println();
        System.out.println(orders2);
        System.out.println(orders3);

        return orders;
    }
}
