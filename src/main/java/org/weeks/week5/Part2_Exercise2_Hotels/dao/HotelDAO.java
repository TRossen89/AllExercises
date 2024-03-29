package org.weeks.week5.Part2_Exercise2_Hotels.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.weeks.week4.part1_JPQL_ManyToOne.model.Driver;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Hotel;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Room;

import java.util.ArrayList;
import java.util.List;

public class HotelDAO extends DAO<Hotel> {

    public HotelDAO(EntityManagerFactory emf) {
        super(emf);
    }


    public Hotel create (Hotel entity){
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            Long generatedId = entity.getId();

            return em.find(Hotel.class, generatedId);
        }

    }

    public List<Hotel> getAll(){

        try(var em = emf.createEntityManager()){
            var query = em.createQuery("SELECT a FROM Hotel a", Hotel.class);

            return query.getResultList();
        }
    }

    @Override
    public Hotel getById(Long id) {

        try(var em = emf.createEntityManager()){
            Hotel hotel = em.find(Hotel.class, id);
            return hotel;
        }
    }

    public List<Room> getAllRoomsFromHotelByHotelId(int id){

        try(var em = emf.createEntityManager()){
            var query = em.createQuery("SELECT r FROM Hotel a JOIN a.rooms r WHERE a.id = :id", Room.class)
                    .setParameter("id", id);

            return query.getResultList();
        }
    }

    public Hotel update(Hotel entity){
        try (var em = emf.createEntityManager()) {

            em.getTransaction().begin();
            //Hotel hotelFromDBToUpdate = em.find(Hotel.class, id);
            //hotelFromDBToUpdate.setHotelName(entity.getHotelName());
            //hotelFromDBToUpdate.setAddress(entity.getAddress());
            em.merge(entity);
            em.getTransaction().commit();

            Long idFromUpdatedEntity = entity.getId();
            Hotel hotelUpdated = em.find(Hotel.class, idFromUpdatedEntity);

            return hotelUpdated;
        }
    };



}
