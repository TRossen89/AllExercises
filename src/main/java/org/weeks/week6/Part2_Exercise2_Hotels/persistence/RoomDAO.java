package org.weeks.week6.Part2_Exercise2_Hotels.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class RoomDAO extends DAO<org.weeks.week6.Part2_Exercise2_Hotels.model.Room> {


    public RoomDAO(EntityManagerFactory emf) {
        super(emf);
    }


    public org.weeks.week6.Part2_Exercise2_Hotels.model.Room create(org.weeks.week6.Part2_Exercise2_Hotels.model.Room entity){
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            Long generatedId = entity.getId();
            return em.find(org.weeks.week6.Part2_Exercise2_Hotels.model.Room.class, generatedId);
        }
    }
    public List<org.weeks.week6.Part2_Exercise2_Hotels.model.Room> getAll(){

        try(var em = emf.createEntityManager()){
            var query = em.createQuery("SELECT a FROM Room a", org.weeks.week6.Part2_Exercise2_Hotels.model.Room.class);
            return query.getResultList();
        }
    }

    @Override
    public org.weeks.week6.Part2_Exercise2_Hotels.model.Room getById(Long id) {

        try(var em = emf.createEntityManager()){
            return em.find(org.weeks.week6.Part2_Exercise2_Hotels.model.Room.class, id);
        }
    }

    public org.weeks.week6.Part2_Exercise2_Hotels.model.Room update(org.weeks.week6.Part2_Exercise2_Hotels.model.Room entity){
        try (var em = emf.createEntityManager()) {

            em.getTransaction().begin();
            //Room roomFromDBToUpdate = em.find(Room.class, id);
            //roomFromDBToUpdate.setHotel(entity.getHotel());
            //roomFromDBToUpdate.setNumber(entity.getNumber());
            //roomFromDBToUpdate.setPrice(entity.getPrice());
            em.merge(entity);
            em.getTransaction().commit();

            Long idFromUpdatedEntity = entity.getId();
            return em.find(org.weeks.week6.Part2_Exercise2_Hotels.model.Room.class, idFromUpdatedEntity);

        }
    }

}
