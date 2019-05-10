package lesson35.order;

import lesson35.exceptions.BadRequestException;
import lesson35.room.Room;
import lesson35.user.User;

import java.util.ArrayList;

public class OrderService {
    OrderRepository orderRepository = new OrderRepository();

    void bookRoom(long roomId, long userId, long hotelId) throws Exception{
        //проверить юзера в базе данных
        User user = validationUser(userId);
        //проверить наличие комнаты в отеле
        //проверить забронирована ли такая комната
        Room room = validationRoom(roomId, hotelId);

        orderRepository.bookRoom(room, user);
    }

    void cancelReservation(long roomId, long userId) throws Exception{
        orderRepository.cancelReservation(roomId, userId);
    }

    private Room validationRoom(long roomId, long hotelId) throws Exception{
        Room room = (Room) orderRepository.findObjectById(roomId, orderRepository.getRoomRepository());

        if (room == null)
            throw new BadRequestException("Room with id " + roomId + " does not exist! Method " + Thread.currentThread().getStackTrace()[2].getMethodName());

        if (room.getHotel().getId() != hotelId)
            throw new BadRequestException("Room with id " + roomId + " does not exist in hotel with id " + hotelId + ". Method " + Thread.currentThread().getStackTrace()[2].getMethodName());

        ArrayList<Order> orders = orderRepository.getList();
        for (Order order : orders) {
            if (order.getRoom().getId() == roomId)
                throw new BadRequestException("Room with id " + roomId + " already reserved! Method " + Thread.currentThread().getStackTrace()[2].getMethodName());
        }
        return room;
    }

    private User validationUser(long userId) throws Exception{
        User user = (User) orderRepository.findObjectById(userId, orderRepository.getUserRepository());

        if (user == null)
            throw new BadRequestException("User with id " + userId + " does not registered. Method " + Thread.currentThread().getStackTrace()[2].getMethodName());

        return user;
    }
}
