package org.weeks.week5.Part2_Exercise2_Hotels.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.weeks.week4.part1_JPQL_ManyToOne.model.Driver;
import org.weeks.week5.Part2_Exercise2_Hotels.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelDAO extends DAO<Hotel> {

    public HotelDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Hotel> getAll(){

        try(var em = emf.createEntityManager()){
            var query = em.createQuery("SELECT a FROM Hotel a", Hotel.class);

            //query.getResultList().forEach(System.out::println);

            return query.getResultList();
        }
    }

    @Override
    public Hotel getById(int id) {

        try(var em = emf.createEntityManager()){
            return em.find(Hotel.class, id);
        }
    }



}
