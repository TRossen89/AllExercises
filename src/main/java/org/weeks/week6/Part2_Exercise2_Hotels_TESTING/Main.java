package org.weeks.week6.Part2_Exercise2_Hotels_TESTING;

import jakarta.persistence.EntityManagerFactory;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.apiEndpoints.Endpoints;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.config.ApiConfig;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.persistence.HibernateConfig;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.persistence.HotelDAO;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.persistence.IDAO;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.persistence.RoomDAO;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.model.Hotel;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.model.Room;

import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.put;

public class Main {

    private static EntityManagerFactory emf;

    public static void main(String[] args) {

        emf = HibernateConfig.getEntityManagerFactoryConfig("hoteldb", false);


        IDAO hotelIDAO = new HotelDAO(emf);
        IDAO roomDAO = new RoomDAO(emf);

        Hotel hotel = new Hotel("Palads", "Some address 1");

        Room room = new Room(10, 1800.00);
        hotel.addRoom(room);

        hotelIDAO.create(hotel);

        Room roomTest = new Room(11, 1200.00);

        Hotel hotelTest = new Hotel("Test", "Tester street 1");
        hotelTest.addRoom(roomTest);

        hotelIDAO.create(hotelTest);


        System.out.println("\n-----------ALL HOTELS------------");
        List<Hotel> allHotels = hotelIDAO.getAll();
        allHotels.forEach(System.out::println);

        HotelDAO hotelDAO = new HotelDAO(emf);
        List<Room> rooms = hotelDAO.getAllRoomsFromHotelByHotelId(2);
        rooms.forEach(System.out::println);


    /*
        System.out.println("\n------------HOTEL 1------------");
        System.out.println(hotelIDAO.getById(1));

        System.out.println("\n-----------ALL HOTELS AFTER DELETE AND UPDATE------------");

        hotelIDAO.delete(hotelTest);

        hotel.setHotelName("PaladsUpdated");
        hotelIDAO.update(hotel);

        List<Hotel> allHotelsAfterDelete = hotelIDAO.getAll();

        allHotelsAfterDelete.forEach(System.out::println);
 */

        ApiConfig
                .getInstance()
                .initiateServer()
                .errorHandling()
                .startServer(7007)
                .setRoutes(Endpoints.hotels(emf))
                .setRoutes(Endpoints.rooms(emf));
    }

}
