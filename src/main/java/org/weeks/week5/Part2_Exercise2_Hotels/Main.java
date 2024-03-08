package org.weeks.week5.Part2_Exercise2_Hotels;

import jakarta.persistence.EntityManagerFactory;
import org.weeks.week5.Part2_Exercise2_Hotels.config.HibernateConfig;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.HotelDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.IDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.RoomDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Hotel;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Room;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hoteldb", false);


        IDAO hotelDAO = new HotelDAO(emf);
        IDAO roomDAO = new RoomDAO(emf);

        Hotel hotel = new Hotel("Palads", "Some address 1");

        Room room = new Room(10, 1800.00);
        hotel.addRoom(room);

        hotelDAO.create(hotel);

        Room roomTest = new Room(11, 1200.00);

        Hotel hotelTest = new Hotel("Test", "Tester street 1");
        hotelTest.addRoom(roomTest);

        hotelDAO.create(hotelTest);




        System.out.println("\n-----------ALL HOTELS------------");
        List<Hotel> allHotels = hotelDAO.getAll();
        allHotels.forEach(System.out::println);

        System.out.println("\n------------HOTEL 1------------");
        System.out.println(hotelDAO.getById(1));


        System.out.println("\n-----------ALL HOTELS AFTER DELETE AND UPDATE------------");

        hotelDAO.delete(hotelTest);

        hotel.setHotelName("PaladsUpdated");
        hotelDAO.update(hotel);

        List<Hotel> allHotelsAfterDelete = hotelDAO.getAll();

        allHotelsAfterDelete.forEach(System.out::println);






/*

        ApiConfig
                .getInstance()
                .initiateServer()
                .errorHandling()
                .startServer(7007)
                .setRoutes(patients())
                .setRoutes(appointments());

 */
    }
}
