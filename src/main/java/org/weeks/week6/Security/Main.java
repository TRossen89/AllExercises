package org.weeks.week6.Security;


import jakarta.persistence.EntityManagerFactory;

import org.weeks.week6.Security.config.ApiConfig;
import org.weeks.week6.Security.model.Hotel;
import org.weeks.week6.Security.model.Role;
import org.weeks.week6.Security.model.Room;
import org.weeks.week6.Security.model.User;
import org.weeks.week6.Security.persistence.HibernateConfig;
import org.weeks.week6.Security.persistence.daos.HotelDAO;
import org.weeks.week6.Security.persistence.daos.IDAO;
import org.weeks.week6.Security.persistence.daos.UserDao;
import org.weeks.week6.Security.routes.AllRoutes;

public class Main {

    private static EntityManagerFactory emf;

    public static void main(String[] args) {

        emf = HibernateConfig.getEntityManagerFactoryConfig("securitydb", false);


        IDAO hotelIDAO = new HotelDAO(emf);

        Hotel hotel = new Hotel("Palads", "Some address 1");

        Room room = new Room(10, 1800.00);
        hotel.addRoom(room);

        hotelIDAO.create(hotel);

        Room roomTest = new Room(11, 1200.00);

        Hotel hotelTest = new Hotel("Test", "Tester street 1");
        hotelTest.addRoom(roomTest);

        hotelIDAO.create(hotelTest);

        //SecurityController securityControllerInstantiated = SecurityController.getInstance();

        UserDao userDao = new UserDao();

        Role role1 = new Role("user");
        Role role2 = new Role("admin");
        userDao.createRole(role1);
        userDao.createRole(role2);

        User user1 = new User("Jonas", "1234");

        userDao.createUser(user1);

        userDao.addRoleToUser(role2, user1);

        ApiConfig
                .getInstance()
                .initiateServer()
                .setRoutes(AllRoutes.getSecurityRoutes())
                .setRoutes(AllRoutes.getSecuredRoutes())
                .setRoutes(AllRoutes.hotels(emf))
                .setRoutes(AllRoutes.rooms(emf))
                .setGeneralExceptionHandling()
                .startServer(7007)
                .checkSecurityRoles()
                .errorHandling()
                .setApiExceptionHandling();



    }
}
