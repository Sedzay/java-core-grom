package lesson35.room;

import lesson35.Filter;

import java.util.ArrayList;
import java.util.HashSet;

public class RoomController {
    private RoomService roomService = new RoomService();

    public Room addRoom(Room room) throws Exception{
        return roomService.addRoom(room);
    }

    public void deleteRoom(long RoomId) throws Exception{
        roomService.deleteRoom(RoomId);
    }

    public ArrayList findRooms(Filter filter) throws Exception {
        return roomService.findRooms(filter);
    }
}
