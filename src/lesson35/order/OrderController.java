package lesson35.order;

public class OrderController {
    OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, long hotelId) throws Exception{
        orderService.bookRoom(roomId, userId, hotelId);
    }

    public void cancelReservation(long roomId, long userId) throws Exception{
        orderService.cancelReservation(roomId, userId);
    }
}
