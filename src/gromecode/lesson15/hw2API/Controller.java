package gromecode.lesson15.hw2API;

public class Controller {
    private API[] apis;

    public Controller(API[] apis) {
        this.apis = apis;
    }

    //-----------------------------------------------
    public Room[] requestRooms(int price, int persons, String city, String hotel) {
        if(!checkArrayAPI())
            return null;
        int countRooms = 0;
        for (API api : apis) {
            if (api != null && api.findRooms(price, persons, city, hotel) != null) {
                countRooms += api.findRooms(price, persons, city, hotel).length;
            }
        }
        Room[] rooms = new Room[countRooms];
        int i = 0;
        for (API api : apis) {
            if (api != null && api.findRooms(price, persons, city, hotel)!=null) {
                Room[] apiRooms = api.findRooms(price, persons, city, hotel);
                for (Room room : apiRooms) {
                    rooms[i] = room;
                    i++;
                }
            }
        }
        return rooms;
    }

    //--------------------------------------------------
    public Room[] check(API api1, API api2) {
        if (api1 == null || api2 == null)
            return null;

        Room[] roomsAPI1 = api1.getAll();
        Room[] roomsAPI2 = api2.getAll();
        int countRooms = 0;

        for (Room room1 : roomsAPI1) {
            for (Room room2 : roomsAPI2) {
                if(room1 != null && room2 != null && room1.equals(room2) && room1.hashCode() == room2.hashCode())
                    countRooms++;
            }
        }

        Room[] resultRooms = new Room[countRooms];
        int i = 0;
        for (Room room1 : roomsAPI1) {
            for (Room room2 : roomsAPI2) {
                if (room1 != null && room2 != null && room1.equals(room2) && room1.hashCode() == room2.hashCode()) {
                    resultRooms[i] = room1;
                    i++;
                }
            }
        }
        return resultRooms;
    }

    //----------------------------------------------------

    public Room cheapestRoom() {
        if(!checkArrayAPI())
            return null;
        Room mostCheapestRoom = null;
        for (API api : apis) {
            if (api != null) {
                for (Room room : api.getAll()) {
                    if (room != null && (mostCheapestRoom == null || mostCheapestRoom.getPrice() > room.getPrice()))
                        mostCheapestRoom = room;
                }
            }
        }
        return mostCheapestRoom;
    }


    private boolean checkArrayAPI() {
        return apis != null;
    }
}
