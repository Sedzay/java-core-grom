package lesson35.hotel;


import lesson35.exceptions.BadRequestException;

import java.util.ArrayList;

public class HotelService {
    private HotelRepository hotelRepository = new HotelRepository();

    Hotel findHotelByName(String name) throws Exception{
        return hotelRepository.findHotelByName(name);
    }

    Hotel findHotelByCity(String city) throws Exception {
        return hotelRepository.findHotelByCity(city);
    }

    Hotel addHotel(Hotel hotel) throws Exception{

        //TODO add checking on UserType == admin
//        if (loginUser.getUserType() != UserType.ADMIN)
//            throw new BadRequestException("You must login how admin. Method: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        checkFieldsHotel(hotel);
        checkReiterationHotel(hotel);

        return hotelRepository.addHotel(hotel);
    }

    void deleteHotel(long hotelId) throws Exception{
        //TODO add checking on UserType == admin
//      if (loginUser.getUserType() != UserType.ADMIN)
//          throw new BadRequestException("You must login how admin. Method: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        hotelRepository.deleteHotel(hotelId);
    }

    private void checkFieldsHotel(Hotel hotel) throws BadRequestException {
        if (hotel.getName() == null || hotel.getName().equals(""))
            throw new BadRequestException("Field Name must be filled");

        if (hotel.getCity() == null || hotel.getCity().equals(""))
            throw new BadRequestException("Field City must be filled");

        if (hotel.getCountry() == null || hotel.getCountry().equals(""))
            throw new BadRequestException("Field Country must be filled");

        if (hotel.getStreet() == null || hotel.getStreet().equals(""))
            throw new BadRequestException("Field Street must be filled");
    }

    private void checkReiterationHotel(Hotel hotel) throws Exception{
        for (Hotel hotelFromDb : (ArrayList<Hotel>) hotelRepository.mappingStringsToObjects(hotelRepository.readFile())) {
            if (hotelFromDb.equals(hotel))
                throw new BadRequestException("Hotel with name " + hotel.getName() + " in city " + hotel.getCity() + " already exist. Method " + Thread.currentThread().getStackTrace()[2].getMethodName());
        }
    }
}
