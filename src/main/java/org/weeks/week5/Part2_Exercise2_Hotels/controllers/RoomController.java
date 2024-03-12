package org.weeks.week5.Part2_Exercise2_Hotels.controllers;

import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import jakarta.persistence.EntityManagerFactory;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.HotelDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.IDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.RoomDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.dtos.HotelDTO;
import org.weeks.week5.Part2_Exercise2_Hotels.dtos.RoomDTO;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Hotel;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Room;

import java.util.List;

public class RoomController {


    EntityManagerFactory emf;

    public RoomController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Handler getAll() {

        return ctx -> {
            IDAO roomIDAO = new RoomDAO(emf);
            List<Room> rooms = roomIDAO.getAll();

            ctx.status(HttpStatus.OK).json(RoomDTO.getEntities(rooms));
        };
    }

    public Handler getById() {

        return ctx-> {
          Long id = Long.parseLong(ctx.pathParam("id"));
          RoomDAO roomDAO = new RoomDAO(emf);
          ctx.status(200).json(new RoomDTO(roomDAO.getById(id)));

        };
    }

    public Handler create() {

        return ctx -> {

            RoomDAO roomDAO = new RoomDAO(emf);
            Room newRoom = ctx.bodyAsClass(Room.class);
            Room roomCreated = roomDAO.create(newRoom);
            ctx.status(201).json("Room created: " + roomCreated);
        };
    }

    public Handler updateRoom() {

        return ctx -> {
            Long id = Long.parseLong(ctx.pathParam("id"));

            RoomDAO roomDAO = new RoomDAO(emf);
            Room roomBeforeUpdate = roomDAO.getById(id);

            Room room = ctx.bodyAsClass(Room.class);
            room.setId(id);

            // Should maybe be converted to a DTO list
            Room roomUpdated = roomDAO.update(room);

            ctx.status(201).json("Room updated (before update): " + roomBeforeUpdate
                    + "\nRoom after update: " + roomUpdated);

        };
    }

    public Handler deleteRoom() {

    return ctx -> {
        Long id = Long.parseLong(ctx.pathParam("id"));

        RoomDAO roomDAO = new RoomDAO(emf);
        Room roomToDelete = roomDAO.getById(id);
        roomDAO.delete(roomToDelete);

        ctx.status(200).json("Deleted room: " + roomToDelete);
    };


    }
}
