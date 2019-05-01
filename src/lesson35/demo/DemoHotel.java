package lesson35.demo;

import lesson35.LoginLogoutUser;
import lesson35.hotel.Hotel;
import lesson35.hotel.HotelController;

public class DemoHotel {
    public static void main(String[] args) throws Exception{

        //Hotel hotel = new Hotel(0, "DneprHotel", "Ukraine", "Dnipro", "Naberegnaya");

        HotelController hotelController = new HotelController();

        System.out.println(hotelController.findHotelByName("Hilton"));
        //System.out.println(hotelController.findHotelByName("test"));

        System.out.println(hotelController.findHotelByCity("Kiev"));
        //System.out.println(hotelController.findHotelByCity("Odessa"));

//        Hotel hotel = new Hotel(0,"Dnepr", "Ukraine", "Dnipro", "Naberegnaya st.");
//        System.out.println(hotelController.addHotel(hotel));

        hotelController.deleteHotele(102);
    }

}
