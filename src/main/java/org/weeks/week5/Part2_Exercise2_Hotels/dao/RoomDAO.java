package org.weeks.week5.Part2_Exercise2_Hotels.dao;

import jakarta.persistence.EntityManagerFactory;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Hotel;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Room;

import java.util.List;

public class RoomDAO extends DAO<Room> {


    public RoomDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Room> getAll(){

        try(var em = emf.createEntityManager()){
            var query = em.createQuery("SELECT a FROM Room a", Room.class);
            return query.getResultList();
        }
    }

    @Override
    public Room getById(int id) {

        try(var em = emf.createEntityManager()){
            return em.find(Room.class, id);
        }
    }

}