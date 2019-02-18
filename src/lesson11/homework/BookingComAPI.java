package lesson11.homework;

public class BookingComAPI implements API {

    private Room[] rooms;

    public BookingComAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {
        Room[] newArrayRooms = null;
        if (rooms == null)
            return null;
        //посчитать размер массива нужных комнат
        int countRooms = 0;
        for (Room room : rooms) {
            if (needRoom(room, price, persons, city, hotel)) {
                countRooms++;
            }
        }

        //заполнить массив нужных комнат
        if (countRooms > 0) {
            newArrayRooms = new Room[countRooms];
            int i = 0;
            for (Room room : rooms) {
                if (needRoom(room, price, persons, city, hotel)) {
                    newArrayRooms[i] = room;
                    i++;
                }
            }
        }
        return newArrayRooms;
    }

    @Override
    public Room[] getAll() {
        return rooms;
    }

    private boolean needRoom(Room room, int price, int persons, String city, String hotel) {
        boolean result = false;
        if (room != null
                && (room.getPrice() - 100 <= price && price <= room.getPrice() + 100)
                && room.getPersons() == persons
                && room.getCityName() == city
                && room.getHotelName() == hotel) {
            result = true;
        }
        return result;
    }
}
