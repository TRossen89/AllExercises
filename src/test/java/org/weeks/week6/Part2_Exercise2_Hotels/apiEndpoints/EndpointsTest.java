package org.weeks.week6.Part2_Exercise2_Hotels.apiEndpoints;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.apiEndpoints.Endpoints;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.model.Hotel;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.model.Room;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.persistence.HibernateConfig;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.config.ApiConfig;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.persistence.HotelDAO;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.persistence.IDAO;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

class EndpointsTest {
    static EntityManagerFactory emfTest;

    @BeforeAll
    static void beforeAll() {

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
                .body("size()", greaterThan(0))
                .body("id", hasItem(1))
                .body("hotelName", hasItem("Test"))
                .body("hotelName", hasItem("Palads"))
                // This is not useful because the elements are returned in random order
                .body("[1].id", equalTo(2));



    }

    @Test
    void hotelById() {

        given()
                .when()
                .get("http://localhost:7007/api/hotels/2")
                .then()
                .statusCode(200)

                .body("size()", greaterThan(0))

                .body("id", is(2))

                .body("name", equalTo("Test"))

                .body("address", is("Tester street 1"));

    }

    @Test
    void createHotel() {
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
                .body("size()", greaterThan(0))
                .body("id", is(3));

    }


    @Test
    void updateHotel() {
        with()
                .body(new Hotel("Hotel 2", "Hotel Street 2"))
                .when()
                .request("PUT", "http://localhost:7007/api/hotels/2")
                .then()
                .statusCode(201);

        given()
                .when()
                .get("http://localhost:7007/api/hotels/2")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .assertThat()
                .body("id", is(2))
                .assertThat()
                .body("name", equalTo("Hotel 2"));

    }

    @Test
    void deleteHotel() {
        given()
                .when()
                .request("DELETE", "http://localhost:7007/api/hotels/2")
                .then()
                .statusCode(200)
                .body("id", is(2))
                .body("hotelName", equalTo("Test"))
                .body("address", equalTo("Tester street 1"));


    }

    @Test
    void rooms() {
    }
}