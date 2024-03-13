package org.weeks.week6.Part2_Exercise2_Hotels.apiEndpoints;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.weeks.week6.Part2_Exercise2_Hotels.model.Hotel;
import org.weeks.week6.Part2_Exercise2_Hotels.model.Room;
import org.weeks.week6.Part2_Exercise2_Hotels.persistence.HibernateConfig;
import org.weeks.week6.Part2_Exercise2_Hotels.config.ApiConfig;
import org.weeks.week6.Part2_Exercise2_Hotels.persistence.HotelDAO;
import org.weeks.week6.Part2_Exercise2_Hotels.persistence.IDAO;
import org.weeks.week6.Part2_Exercise2_Hotels.persistence.RoomDAO;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

class EndpointsTest {
    static EntityManagerFactory emfTest;

    @BeforeAll
    static void beforeAll(){

        emfTest = HibernateConfig.getEntityManagerFactoryConfig("week6", true);

        IDAO hotelIDAO = new HotelDAO(emfTest);

        // Creating 2 hotels with rooms
        Hotel hotel = new Hotel("Palads", "Some address 1");
        Room room = new Room(10, 1800.00);
        hotel.addRoom(room);

        hotelIDAO.create(hotel);

        Room roomTest = new Room(11, 1200.00);
        Hotel hotelTest = new Hotel("Test", "Tester street 1");
        hotelTest.addRoom(roomTest);

        hotelIDAO.create(hotelTest);

    }
    @BeforeEach
    void setUp() {
        ApiConfig
                .getInstance()
                .initiateServer()
                .errorHandling()
                .startServer(7007)
                .setRoutes(Endpoints.hotels(emfTest))
                .setRoutes(Endpoints.rooms(emfTest));

    }

    @AfterEach
    void tearDown() {
        ApiConfig.getInstance().stopServer();
    }

    @Test
    void allHotels() {

        given()
                .when()
                .get("http://localhost:7007/api/hotels")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", greaterThan(0))
                .assertThat()
                .body("id", hasItem(1))
                .assertThat()
                .body("name", hasItem("Test"));


    }

    @Test
    void hotelById(){

        given()
                .when()
                .get("http://localhost:7007/api/hotels/2")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", greaterThan(0))
                .assertThat()
                .body("id", is(2))
                .assertThat()
                .body("name", equalTo("Test"))
                .assertThat()
                .body("address", is("Tester street 1"));

    }

    @Test
    void createHotel(){
        with()
                .body(new Hotel("Hotel 5", "Hotel Street 5"))
                .when()
                .request("POST", "http://localhost:7007/api/hotels")
                .then()
                .statusCode(201);

        given()
                .when()
                .get("http://localhost:7007/api/hotels/3")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", greaterThan(0))
                .assertThat()
                .body("id", is(3));

    }

    @Test
    void rooms() {
    }
}