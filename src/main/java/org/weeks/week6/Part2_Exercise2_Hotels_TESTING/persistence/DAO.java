package org.weeks.week6.Part2_Exercise2_Hotels_TESTING.persistence;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public abstract class DAO<T> implements IDAO<T> {

    EntityManagerFactory emf;

    public DAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public abstract List<T> getAll();
    public abstract T getById(Long id);

    public void delete(T entity){
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }

    };

}
