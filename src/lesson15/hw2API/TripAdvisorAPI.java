package lesson15.hw2API;

import java.util.Date;

public class TripAdvisorAPI implements API{

    private Room[] rooms;

    public TripAdvisorAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {
        Room[] arrayRooms = null;
        int countRooms = 0;
        if (checkRoomsForNull())
            return null;
        for (Room room : rooms) {
            if (needRoom(room,price,persons,city,hotel))
                countRooms++;
        }

        if (countRooms!=0) {
            arrayRooms = new Room[countRooms];
            int i = 0;
            for (Room room : rooms) {
                if (needRoom(room,price,persons,city,hotel)) {
                    arrayRooms[i] = room;
                    i++;
                }

            }
        }
        return arrayRooms;
    }

    @Override
    public Room[] getAll() {
        return rooms;
    }

    private boolean needRoom(Room room, int price, int persons, String city, String hotel) {
        if (room == null)
            return false;
        if (room.getPrice() == price
                && room.getPersons()-1 <= persons
                && persons <= room.getPersons()+1
                && room.getCityName() == city
                && room.getHotelName() == hotel) {
            return true;
        }
        return false;
    }

    private boolean checkRoomsForNull() {
        return rooms == null;
    }
}
