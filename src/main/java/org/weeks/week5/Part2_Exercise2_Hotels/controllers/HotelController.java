package org.weeks.week5.Part2_Exercise2_Hotels.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import jakarta.persistence.EntityManagerFactory;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.HotelDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.dao.IDAO;
import org.weeks.week5.Part2_Exercise2_Hotels.dtos.HotelDTO;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Hotel;

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




}
