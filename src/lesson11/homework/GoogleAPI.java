package lesson11.homework;

public class GoogleAPI implements API {

    private Room[] rooms;

    public GoogleAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {
        Room[] newArrayRooms = null;

        //посчитать размер массива нужных комнат
        int countRooms = 0;
        if(rooms == null)
            return null;
        for (Room room : rooms) {
            if (room != null
                    && price == room.getPrice()
                    && room.getPersons() == persons
                    && room.getCityName() == city
                    && room.getHotelName() == hotel) {
                countRooms++;
            }
        }

        //заполнить массив нужных комнат
        if (countRooms > 0) {
            newArrayRooms = new Room[countRooms];
            for (int i = 0, j = 0; i < rooms.length; i++) {
                if (rooms[i] != null
                        && price == rooms[i].getPrice()
                        && rooms[i].getPersons() == persons
                        && rooms[i].getCityName() == city
                        && rooms[i].getHotelName() == hotel) {
                    newArrayRooms[j] = rooms[i];
                    j++;
                }
            }
        }
        return newArrayRooms;
    }

    @Override
    public Room[] getAll() {
        return rooms;
    }
}
