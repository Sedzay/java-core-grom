package lesson35.order;

import lesson35.AbstractRepository;
import lesson35.exceptions.BadRequestException;
import lesson35.exceptions.InternalServerException;
import lesson35.room.Room;
import lesson35.room.RoomRepository;
import lesson35.user.User;
import lesson35.user.UserRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class OrderRepository extends AbstractRepository {
    private final String pathOrder = "D:\\DataBaseFiles\\OrderDb.txt";
    private final String pathUser = "D:\\DataBaseFiles\\UserDb.txt";
    private final String pathRoom = "D:\\DataBaseFiles\\RoomDb.txt";

    private UserRepository userRepository = new UserRepository();
    private RoomRepository roomRepository = new RoomRepository();

    public OrderRepository() {
        super.setPath(pathOrder);
        userRepository.setPath(pathUser);
        roomRepository.setPath(pathRoom);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RoomRepository getRoomRepository() {
        return roomRepository;
    }

    void bookRoom(Room room, User user) throws Exception{

        Date bookedUp = new Date();
        Order order = new Order(addId(getList()), user, room, new Date(), bookedUp, 0);
        save(order);

        //изменить дату свободной комнаты
        updateRoomDateAviableTo(room, bookedUp);

        System.out.println("Room was booking successfully!");
    }

    void cancelReservation(long roomId, long userId) throws Exception{
        ArrayList<Order> orders = getList();
        if (orders.isEmpty())
            throw new InternalServerException("Orders Data base is empty. Method " + Thread.currentThread().getStackTrace()[1].getMethodName());

        for (Order order : orders) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId) {
                orders.remove(order);
                writeListObjectsToDb(orders);
                System.out.println("Order by room with id " + roomId + " removed successfully!");
                return;
            }
        }
        throw new BadRequestException("Order with room id " + roomId + " and user id " + userId + " does not exist. Method " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Override
    public Order stringToObject(String[] attributes) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        User user = (User) findObjectById(Long.parseLong(attributes[1]), userRepository);
        Room room = (Room) findObjectById(Long.parseLong(attributes[2]), roomRepository);

        return new Order(Long.parseLong(attributes[0]),
                user,
                room,
                dateFormat.parse(attributes[3]),
                dateFormat.parse(attributes[4]),
                Double.parseDouble(attributes[5]));
    }

    private void updateRoomDateAviableTo(Room room, Date bookedUp) throws Exception{
        ArrayList<Room> rooms = roomRepository.getList();
        for (Room r : rooms) {
            if (r.getId() == room.getId()) {
                r.setDateAvailableFrom(new Date(bookedUp.getTime() + (1000 * 60 * 60 * 24)));
                room = r;
            }
        }
        roomRepository.writeListObjectsToDb(rooms);
    }

}
