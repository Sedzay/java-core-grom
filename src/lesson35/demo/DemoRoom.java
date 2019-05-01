package lesson35.demo;

import lesson35.Filter;
import lesson35.hotel.Hotel;
import lesson35.room.Room;
import lesson35.room.RoomController;

import java.util.Date;

public class DemoRoom {
    public static void main(String[] args) throws Exception{
        RoomController roomController = new RoomController();
        Hotel hotel = new Hotel(103, "Hilton", "USA", "NY", "CentralStreet");
        Room room = new Room(0, 2, 12.3, true, false, new Date(), hotel);
        //roomController.addRoom(room);

        //roomController.deleteRoom(103);
        //roomController.deleteRoom(104);
        //roomController.deleteRoom(106);

        //отобрать комнаты по цене, кол-ву людей, завтраку и возможность пребывания с животными
        Filter filter = new Filter(2, 12.3, true, true, null, null, null, null);
        System.out.println(roomController.findRooms(filter));

        //нет таких фильтров
        Filter filter2 = new Filter(0, 0, false, false, null, "SomeHotel", null, null);
        System.out.println(roomController.findRooms(filter2));

        //по стране, животным и завтраку
        Filter filter3 = new Filter(0, 0, true, true, null, null, "Ukraine", null);
        System.out.println(roomController.findRooms(filter3));

    }
}
