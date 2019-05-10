package lesson35.hotel;

import lesson35.AbstractRepository;
import lesson35.exceptions.BadRequestException;
import lesson35.exceptions.InternalServerException;

import java.util.ArrayList;

public class HotelRepository extends AbstractRepository {
    private final String path = "D:\\DataBaseFiles\\HotelDb.txt";

    public HotelRepository() {
        super.setPath(path);
    }

    Hotel findHotelByName(String name) throws Exception {
        ArrayList<Hotel> hotels = getList();
        for (Hotel hotel : hotels) {
            if (hotel != null && hotel.getName().equals(name))
                return hotel;
        }
        throw new BadRequestException("Hotel with name " + name + " not found in method " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    Hotel findHotelByCity(String city) throws Exception {
        ArrayList<Hotel> hotels = getList();
        for (Hotel hotel : hotels) {
            if (hotel.getCity().equals(city))
                return hotel;
        }
        throw new BadRequestException("Hotel in city " + city + " not found in method " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }


    @Override
    public Hotel stringToObject(String[] attributes) throws InternalServerException {
        return new Hotel(Long.parseLong(attributes[0]),
                attributes[1],
                attributes[2],
                attributes[3],
                attributes[4]);
    }

    Hotel addHotel(Hotel hotel) throws Exception {
        hotel.setId(addId(getList()));
        return (Hotel) save(hotel);
    }

    void deleteHotel(long hotelId) throws Exception {
        ArrayList<Hotel> hotels = getList();
        int index = 0;
        for (Hotel hotel : hotels) {
            if (hotel.getId() == hotelId) {
                hotels.remove(index);
                System.out.println("Hotel with id " + hotel.getId() + " remove successfully!");
                writeListObjectsToDb(hotels);
                return;
            }
            index++;
        }
        throw new BadRequestException("Hotel with id " + hotelId + " not found. Method " + Thread.currentThread().getStackTrace()[1].getMethodName());

    }

}
