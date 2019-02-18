package lesson11.homework;

public class Controller {
    private API[] apis;

    public Controller(API[] apis) {
        this.apis = apis;
    }

    //-----------------------------------------------------------
    public Room[] requestRooms(int price, int persons, String city, String hotel) {
        int countRooms = 0;

        for (API api : apis) {
            if (api != null && api.findRooms(price,persons,city,hotel)!=null) {
                //for (Room room : api.getAll()) {
                    countRooms += api.findRooms(price,persons,city,hotel).length;
                    /*if (findRoomByParameters(room,price,persons,city,hotel))
                        countRooms ++;*/
                //}
            }
        }

        Room[] rooms = new Room[countRooms];
        int i = 0;
        for (API api : apis) {
            if (api != null && api.findRooms(price,persons,city,hotel)!=null) {
                Room[] apiRooms = api.findRooms(price,persons,city,hotel);
                for (Room room : /*api.getAll()*/ apiRooms) {
                    //if (findRoomByParameters(room,price,persons,city,hotel)){
                        rooms[i] = room;
                        i++;
                    //}
                }
            }
        }

        return rooms;
    }

    /*private boolean findRoomByParameters(Room room, int price, int persons, String city, String hotel) {
        boolean neededRoom = false;
        if (room != null
                && room.getPrice() == price
                && room.getPersons() == persons
                && room.getCityName() == city
                && room.getHotelName() == hotel)
            neededRoom = true;
        return neededRoom;
    }*/

    //------------------------------------------------------------
    public Room[] check(API api1, API api2) {
        if (api1 == null || api2 == null)
            return null;

        Room[] roomsApi1 = api1.getAll();
        Room[] roomsApi2 = api2.getAll();
        int countRooms = 0;

        for (Room room1 : roomsApi1) {
            for (Room room2 : roomsApi2) {
                if (sameRooms(room1, room2)) {
                    countRooms++;
                }
            }
        }

        Room[] uniqueRooms = new Room[countRooms];
        int i = 0;
        for (Room room1 : roomsApi1) {
            for (Room room2 : roomsApi2) {
                if (sameRooms(room1, room2)) {
                    uniqueRooms[i] = room1;
                    i++;

                }
            }
        }
        return uniqueRooms;
    }

    private boolean sameRooms(Room room1, Room room2) {
        boolean same = false;
        if (room1 != null
                && room2 != null
                && room1.getPrice() == room2.getPrice()
                && room1.getPersons() == room2.getPersons()
                && room1.getCityName() == room2.getCityName()
                && room1.getHotelName() == room2.getHotelName()) {
            same = true;
        }
        return same;
    }
//---------------------------------------------------------------------------

    public Room cheapestRoom() {

        Room mostCheapestRoom = null;

        for (API api : apis) {
            if (api != null) {
                for (Room room : api.getAll()) {
                    if (room != null && (mostCheapestRoom == null || room.getPrice() < mostCheapestRoom.getPrice()))
                        mostCheapestRoom = room;
                }
            }
        }
        return mostCheapestRoom;
    }
}
