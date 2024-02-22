package org.weeks.week3.part1_and_part2_Basic_JPA_CRUD;

import jakarta.persistence.EntityManagerFactory;

public class UnicornDAO {

    EntityManagerFactory entityManagerFactory;

    public UnicornDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void save(Unicorn unicorn) {

        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(unicorn);
            em.getTransaction().commit();
        }
    }

    public void update(Unicorn unicorn) {
        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();

            var unicornName = em.find(Unicorn.class, unicorn.getId());
            unicornName.setName(unicorn.getName());

            em.merge(unicornName);
            em.getTransaction().commit();
        }
    }

    public void delete(int id) {
        try (var em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            var unicorn = em.find(Unicorn.class, id);
            em.remove(unicorn);
            em.getTransaction().commit();
        }
    }

    public Unicorn findById(int id) {
        try (var em = entityManagerFactory.createEntityManager()) {
            return em.find(Unicorn.class, id);
        }
    }
}
