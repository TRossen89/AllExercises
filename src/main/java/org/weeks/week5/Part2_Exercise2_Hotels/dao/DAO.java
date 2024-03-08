package org.weeks.week5.Part2_Exercise2_Hotels.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public abstract class DAO<T> implements IDAO<T> {

    EntityManagerFactory emf;

    public DAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public void create (T entity){
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }


    public abstract List<T> getAll();
    public abstract T getById(int id);

    public void update(T entity){
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }
    };
    public void delete(T entity){
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }

    };

}