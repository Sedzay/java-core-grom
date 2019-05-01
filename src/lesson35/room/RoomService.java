package lesson35.room;

import lesson35.Filter;
import lesson35.exceptions.BadRequestException;

import java.util.ArrayList;

public class RoomService {
    private RoomRepository roomRepository = new RoomRepository();

    Room addRoom(Room room) throws Exception{
        //TODO add checking on UserType == admin

        checkFieldsRoom(room);
        return roomRepository.addRoom(room);
    }

    void deleteRoom(long RoomId) throws Exception{
        //TODO add checking on UserType == admin

        roomRepository.deleteRoom(RoomId);
    }

    ArrayList findRooms(Filter filter) throws Exception {
        return roomRepository.findRooms(filter);
    }


    private void checkFieldsRoom(Room room) throws BadRequestException {
        if (room.getNumberOfGuests() < 0)
            throw new BadRequestException("Field NumberOfGuests must be bigger 0");

        if (room.getPrice() < 0)
            throw new BadRequestException("Field Price can't be less 0");

        if (room.getHotel() == null)
            throw new BadRequestException("Field Hotel must be filled");
    }


}
