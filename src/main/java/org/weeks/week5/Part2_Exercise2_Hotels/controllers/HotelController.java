package org.weeks.week5.Part2_Exercise2_Hotels.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import jakarta.persistence.EntityManagerFactory;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.HotelDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.IDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.dtos.HotelDTO;
import org.weeks.week5.Part2_Exercise2_Hotels.dtos.RoomDTO;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Hotel;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Room;

import java.util.*;

public class HotelController {



    EntityManagerFactory emf;

    public HotelController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Handler getAll() {

        return ctx -> {
            IDAO hotelIDAO = new HotelDAO(emf);
            List<Hotel> hotels = hotelIDAO.getAll();

            ctx.status(HttpStatus.OK).json(HotelDTO.getEntities(hotels));
        };
    }

    public Handler getById(){
        return ctx -> {

            Long id = Long.parseLong(ctx.pathParam("id"));

            HotelDAO hotelDAO = new HotelDAO(emf);

            Hotel hotel = hotelDAO.getById(id);

            ctx.status(HttpStatus.OK).json(HotelDTO.getEntity(hotel));
        };
    }

    public Handler getRoomsFromHotelByHotelId(){

        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));

            HotelDAO hotelDAO = new HotelDAO(emf);

            List<Room> rooms = hotelDAO.getAllRoomsFromHotelByHotelId(id);
            Set<RoomDTO> roomDTOS = RoomDTO.getEntities(rooms);
            roomDTOS.forEach(System.out::println);

            ctx.status(HttpStatus.OK).json(RoomDTO.getEntities(rooms));
        };
    }

    public Handler createHotel(){

        return ctx -> {
            HotelDAO hotelDAO = new HotelDAO(emf);
            Hotel hotel = ctx.bodyAsClass(Hotel.class);
            Hotel hotelWithId = hotelDAO.create(hotel);

            ctx.status(201).json("Following hotel has been stored in the databse: " + hotelWithId);
        };
    }


    public Handler updateHotel() {

        return ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));

            HotelDAO hotelDAO = new HotelDAO(emf);
            Hotel hotelBeforeUpdate = hotelDAO.getById(id);

            Hotel hotel = ctx.bodyAsClass(Hotel.class);
            hotel.setId(id);

            // Should maybe be converted to a DTO list
            Hotel hotelUpdated = hotelDAO.update(hotel);

            ctx.status(201).json("Hotel updated (before update): " + hotelBeforeUpdate
                    + "\nHotel after update: " + hotelUpdated);
        };
    }

    public Handler deleteHotel() {

        return ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));

            HotelDAO hotelDAO = new HotelDAO(emf);
            Hotel hotelToDelete = hotelDAO.getById(id);
            hotelDAO.delete(hotelToDelete);

            ctx.status(200).json(hotelToDelete);
        };
    }
}
