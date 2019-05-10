package lesson35.demo;

import lesson35.order.OrderController;

public class DemoOrder {
    public static void main(String[] args) throws Exception {
        OrderController orderController = new OrderController();
        //orderController.bookRoom(107, 10002, 101);

        //юзер не зарегестрирован
        //orderController.bookRoom(107, 111111, 101);

        //нет такой комнаты
        //orderController.bookRoom(111, 10002, 101);

        //нет такой комнаты в отеле
        //orderController.bookRoom(107, 10002, 103);

        //комната уже зарегестрирована
        //orderController.bookRoom(107, 10002, 101);

        //бронирование комнаты возможно
        orderController.bookRoom(109, 10003, 104);

        //снять бронь, нет такого юзера
        //orderController.cancelReservation(105, 1001);

        //снять бронь, нет такой комнаты
        //orderController.cancelReservation(111, 10002);

        //снять бронь
        //orderController.cancelReservation(105, 10002);

    }
}
