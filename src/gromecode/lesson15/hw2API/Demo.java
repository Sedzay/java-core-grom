package gromecode.lesson15.hw2API;

import java.util.Arrays;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Room room = new Room(1001,100,4,new Date(), "Hilton","Kiev");
        Room room1 = new Room(1002,100,4,new Date(), "Hilton","Kiev");
        Room room2 = new Room(1003,1000,3,new Date(), "Ukraina","Dnepr");
        Room room3 = new Room(1004,100,4,new Date(), "More","Odessa");
        Room room4 = new Room(1005,67,4,new Date(), "Hilton","Kiev");
        Room room5 = new Room(1006,150,4,new Date(), "Hilton","Kiev");

        Room[] rooms = null;

        //test BookingAPI findRoom method
        System.out.println("TEST BOOKINGAPI findRooms METHOD***********");
        BookingComAPI bookingComAPI = new BookingComAPI(rooms);

        //массив комнат пустой
        System.out.println("Array rooms is null: ");
        System.out.println(Arrays.toString(bookingComAPI.findRooms(100,4,"Kiev", "Hilton")));
        System.out.println("-------------------------------------");

        //Массив заплнен, + проверка на null-комнату
        Room[] rooms1 = {null,room,room1,room2,room3,room4,room5};
        bookingComAPI = new BookingComAPI(rooms1);
        System.out.println("Array rooms is not null + room is empty: ");
        System.out.println(Arrays.toString(bookingComAPI.findRooms(100,4,"Kiev", "Hilton")));
        System.out.println("-------------------------------------");

        //test TripAdvisorAPI findRoom method
        System.out.println("TEST TripAdvisorAPI findRooms METHOD***********");
        TripAdvisorAPI tripAdvisorAPI = new TripAdvisorAPI(rooms1);
        System.out.println("Array rooms is not null + room is empty: ");
        System.out.println(Arrays.toString(tripAdvisorAPI.findRooms(100,3,"Kiev", "Hilton")));
        System.out.println("-------------------------------------");

        //test GoogleAPI findRoom method
        System.out.println("TEST GoogleAPI findRooms METHOD***********");
        GoogleAPI googleAPI = new GoogleAPI(rooms1);
        System.out.println("Array rooms is not null + room is empty: ");
        System.out.println(Arrays.toString(googleAPI.findRooms(100,4,"Kiev", "Hilton")));
        System.out.println("-------------------------------------");

        //test Controller requestRooms method
        System.out.println("TEST Controller requestRooms METHOD***********");
        //если API пустой
        API[] apis = null;
        Controller controller = new Controller(null);
        System.out.println("array API is null: ");
        System.out.println(controller.requestRooms(100,4,"Kiev", "Hilton"));
        System.out.println("-------------------------------------");

        //поиск комнат по всем API, один null
        System.out.println("find rooms on request + one API is null: ");
        API[] apis1 = {bookingComAPI, new TripAdvisorAPI(rooms1),new GoogleAPI(rooms1),null};
        controller = new Controller(apis1);
        System.out.println(Arrays.toString(controller.requestRooms(100,4,"Kiev", "Hilton")));
        System.out.println("-------------------------------------");

        //test Controller check method
        System.out.println("TEST Controller CHECK METHOD***********");
        //один API пустой
        System.out.println("one API is null: ");
        System.out.println(controller.check(bookingComAPI,null));
        System.out.println("-------------------------------------");

        //сравниваем комнаты в API, в одном комната null
        Room[]rooms2 = {room1,null};
        googleAPI = new GoogleAPI(rooms2);
        System.out.println("check same rooms + one is null");
        System.out.println(Arrays.toString(controller.check(tripAdvisorAPI,googleAPI)));
        System.out.println("-------------------------------------");

        //test Controller mostCheapestRoom method
        System.out.println("TEST Controller mostCheapestRoom METHOD***********");
        System.out.println(controller.cheapestRoom());
        System.out.println("-------------------------------------");
    }

}
