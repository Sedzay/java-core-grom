package gromecode.lesson15.hw2API;

import java.util.Date;

public class GoogleAPI implements API{

    private Room[] rooms;

    public GoogleAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {
        Room[] arrayRooms = null;
        Room someRoom = new Room(0, price, persons, new Date(), hotel, city);
        int countRooms = 0;
        if (checkRoomsForNull())
            return null;
        for (Room room : rooms) {
            if (room!=null && room.equals(someRoom) && room.hashCode() == someRoom.hashCode())
                countRooms++;
        }

        if (countRooms!=0) {
            arrayRooms = new Room[countRooms];
            int i = 0;
            for (Room room : rooms) {
                if (room!=null && room.equals(someRoom)&& room.hashCode() == someRoom.hashCode()) {
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

    private boolean checkRoomsForNull() {
        return rooms == null;
    }
}
