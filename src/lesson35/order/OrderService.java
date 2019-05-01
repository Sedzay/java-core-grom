package lesson35.order;

import lesson35.exceptions.BadRequestException;
import lesson35.room.Room;
import lesson35.room.RoomRepository;
import lesson35.user.User;
import lesson35.user.UserRepository;

import java.util.ArrayList;

public class OrderService {
    OrderRepository orderRepository = new OrderRepository();

    void bookRoom(long roomId, long userId, long hotelId) throws Exception{
        //проверить юзера в базе данных
        validationUser(userId);
        //проверить наличие комнаты в отеле
        //проверить забронирована ли такая комната
        validationRoom(roomId, hotelId);

        orderRepository.bookRoom(roomId, userId, hotelId);
    }

    void cancelReservation(long roomId, long userId) throws Exception{
        orderRepository.cancelReservation(roomId, userId);
    }

    private void validationRoom(long roomId, long hotelId) throws Exception{
        RoomRepository roomRepository = orderRepository.getRoomRepository();
        Room room = (Room) orderRepository.findObjectById(roomId, roomRepository);

        if (room == null)
            throw new BadRequestException("Room with id " + roomId + " does not exist! Method " + Thread.currentThread().getStackTrace()[2].getMethodName());

        if (room.getHotel().getId() != hotelId)
            throw new BadRequestException("Room with id " + roomId + " does not exist in hotel with id " + hotelId + ". Method " + Thread.currentThread().getStackTrace()[2].getMethodName());

        ArrayList<Order> orders = orderRepository.mappingStringsToObjects(orderRepository.readFile());
        for (Order order : orders) {
            if (order.getRoom().getId() == roomId)
                throw new BadRequestException("Room with id " + roomId + " already reserved! Method " + Thread.currentThread().getStackTrace()[2].getMethodName());
        }
    }

    private void validationUser(long userId) throws Exception{
        UserRepository userRepository = orderRepository.getUserRepository();
        User user = (User) orderRepository.findObjectById(userId, userRepository);

        if (user == null)
            throw new BadRequestException("User with id " + userId + " does not registered. Method " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }
}
