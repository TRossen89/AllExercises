package org.weeks.week5.Part2_Exercise2_Hotels;

import jakarta.persistence.EntityManagerFactory;
import org.weeks.week5.Part2_Exercise2_Hotels.config.HibernateConfig;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.HotelDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.IDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Hotel;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Room;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hoteldb", false);


        IDAO hotelDAO = new HotelDAO(emf);

        Hotel hotel = new Hotel("Palads", "Some address 1");

        Room room = new Room(10, 1800.00);
        hotel.addRoom(room);

        hotelDAO.create(hotel);

        Room roomTest = new Room(11, 1200.00);

        Hotel hotelTest = new Hotel("Test", "Tester street 1");

        roomTest.setHotel(hotelTest);
        hotelDAO.create(hotelTest);

        hotelDAO.create(roomTest);

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
