package org.weeks.week6.Part2_Exercise2_Hotels_TESTING.apiEndpoints;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.controllers.HotelController;
import org.weeks.week6.Part2_Exercise2_Hotels_TESTING.controllers.RoomController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Endpoints {

    public static EndpointGroup hotels(EntityManagerFactory emf) {
        HotelController hotelController = new HotelController(emf);
        return () -> {
            path("/hotels", () -> {
                get("/", hotelController.getAll());
                post("/", hotelController.createHotel());
                path("/{id}", () -> {
                    get(hotelController.getById());
                    put(hotelController.updateHotel());
                    delete(hotelController.deleteHotel());
                    get("/rooms", hotelController.getRoomsFromHotelByHotelId());
                });
            });
        };
    }

    public static EndpointGroup rooms(EntityManagerFactory emf) {
        RoomController roomController = new RoomController(emf);
        return () -> {
            path("/rooms", () -> {
                get("/", roomController.getAll());
                post("/", roomController.create());
                path("/{id}", () -> {
                    get(roomController.getById());
                    put(roomController.updateRoom());
                    delete(roomController.deleteRoom());
                });
            });
        };
    }
}
