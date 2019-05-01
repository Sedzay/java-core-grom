package lesson35.room;

import lesson35.AbstractRepository;
import lesson35.Filter;
import lesson35.exceptions.BadRequestException;
import lesson35.exceptions.InternalServerException;
import lesson35.hotel.Hotel;
import lesson35.hotel.HotelRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RoomRepository extends AbstractRepository {
    private final String path = "D:\\DataBaseFiles\\RoomDb.txt";
    private final String pathHotelDb = "D:\\DataBaseFiles\\HotelDb.txt";

    public RoomRepository() {
        super.setPath(path);
    }

    Room addRoom(Room room) throws Exception {
        room.setId(addId(mappingStringsToObjects(readFile())));
        return (Room) addLine(room);
    }

    void deleteRoom(long RoomId) throws Exception {
        ArrayList<Room> rooms = mappingStringsToObjects(readFile());
        int index = 0;
        for (Room room : rooms) {
            if (room.getId() == RoomId) {
                rooms.remove(index);
                System.out.println("Room with id " + RoomId + " remove successfully!");
                writeListObjectsToDb(rooms);
                return;
            }
            index++;
        }
        throw new BadRequestException("Room with id " + RoomId + " not found. Method " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }


    ArrayList findRooms(Filter filter) throws Exception {

        //Два варианта фильтрации:
        //1. отбирать все комнаты, которые соответствуют хотя бы одному входящему параметру
        //2. отбирать все комнаты, которые соответствуют ВСЕМ входящим параметрам

        //Для реализации выбран второй способ

        ArrayList<Room> rooms = mappingStringsToObjects(readFile());
        ArrayList<Room> needRemoveRooms = new ArrayList<>();

        //Отбираются только те данные, которые НЕ соответствуют параметрам отбора
        //Далее они удаляются из всего списка
        for (Room room : rooms) {
            if ((filter.getNumberOfGuests() > 0 && room.getNumberOfGuests() != filter.getNumberOfGuests()) ||
                    (filter.getPrice() > 0 && room.getPrice() != filter.getPrice()) ||
                    room.isBreakfastIncluded() != filter.isBreakfastIncluded() ||
                    room.isPetsAllowed() != filter.isPetsAllowed() ||
                    (filter.getDateAvailableFrom() != null && !room.getDateAvailableFrom().equals(filter.getDateAvailableFrom())) ||
                    (filter.getCity() != null && !room.getHotel().getCity().equals(filter.getCity())) ||
                    (filter.getHotelName() != null && !room.getHotel().getName().equals(filter.getHotelName())) ||
                    (filter.getCountry() != null && !room.getHotel().getCountry().equals(filter.getCountry()))) {
                needRemoveRooms.add(room);
            }

            /*//фильтрация для первго варианта (необходимо исключить повторения использовав HashSet)
            if (room.getNumberOfGuests() == filter.getNumberOfGuests() ||
                    room.getPrice() == filter.getPrice() ||
                    room.isBreakfastIncluded() == filter.isBreakfastIncluded() ||
                    room.isPetsAllowed() == filter.isPetsAllowed() ||
                    room.getDateAvailableFrom().equals(filter.getDateAvailableFrom()) ||
                    room.getHotel().getCity().equals(filter.getCity()) ||
                    room.getHotel().getName().equals(filter.getHotelName()) ||
                    room.getHotel().getCountry().equals(filter.getCountry())) {
                listRooms.add(room);
            }*/
        }
        rooms.removeAll(needRemoveRooms);
        return rooms;
    }

    @Override
    public Room stringToObject(String[] attributes) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        return new Room(Long.parseLong(attributes[0]),
                Integer.parseInt(attributes[1]),
                Double.parseDouble(attributes[2]),
                Boolean.parseBoolean(attributes[3]),
                Boolean.parseBoolean(attributes[4]),
                dateFormat.parse(attributes[5]),
                findHotelById(Long.parseLong(attributes[6])));
    }

    private Hotel findHotelById(long id) throws Exception {
        HotelRepository hotelRepository = new HotelRepository();
        hotelRepository.setPath(pathHotelDb);
        ArrayList<Hotel> hotels = hotelRepository.mappingStringsToObjects(hotelRepository.readFile());
        for (Hotel hotel : hotels) {
            if (hotel.getId() == id)
                return hotel;
        }
        throw new InternalServerException("Hotel with id " + id + " not found in method " + Thread.currentThread().getStackTrace()[3].getMethodName());
    }
}
